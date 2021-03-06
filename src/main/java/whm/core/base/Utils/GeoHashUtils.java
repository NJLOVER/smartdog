package whm.core.base.Utils;

import java.util.BitSet;
import java.util.HashMap;

/**
 * Created by thinkpad on 2015/11/16.
 */
public class GeoHashUtils {
    private static final  double EARTH_RADIUS = 6371000;//赤道半径(单位m)
    private static int numbits = 6 * 5;
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();
    static {
        int i = 0;
        for (char c : digits)
            lookup.put(c, i++);
    }

    /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     * @param lon1 第一点的经度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的经度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     * */
    public static double GetDistance(double lon1,double lat1,double lon2, double lat2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static String encode(double lat,double lon){
        BitSet latBits = getBits(lat,-180,180);
        BitSet lonBits = getBits(lon,-90,90);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < numbits; i++) {
            buffer.append( (lonBits.get(i))?'1':'0');
            buffer.append( (latBits.get(i))?'1':'0');
        }
        return base32(Long.parseLong(buffer.toString(),2));
    }

    private static BitSet getBits(double lat,double floor,double ceiling){
        BitSet set = new BitSet(numbits);
        for(int i = 0;i<numbits;i++){
            double mid = (floor + ceiling) / 2;
            if(lat>=mid){
                set.set(i);
                floor = mid;
            }else{
                ceiling = mid;
            }
        }
        return set;
    }

    public static void main(String[] args) {
        System.out.println(encode(-123.3456, 78.12345).toString());
        System.out.println(encode(123.3451, 78.12342).toString());
    }

    public static String base32(long i) {
        char[] buf = new char[65];
        int charPos = 64;
        boolean negative = (i < 0);
        if (!negative)
            i = -i;
        while (i <= -32) {
            buf[charPos--] = digits[(int) (-(i % 32))];
            i /= 32;
        }
        buf[charPos] = digits[(int) (-i)];

        if (negative)
            buf[--charPos] = '-';
        return new String(buf, charPos, (65 - charPos));
    }


    public static double[] decode(String geohash) {
        StringBuilder buffer = new StringBuilder();
        for (char c : geohash.toCharArray()) {
            int i = lookup.get(c) + 32;
            buffer.append( Integer.toString(i, 2).substring(1) );
        }
        BitSet lonset = new BitSet();
        BitSet latset = new BitSet();
        //even bits
        int j =0;
        for (int i=0; i< numbits*2;i+=2) {
            boolean isSet = false;
            if ( i < buffer.length() )
                isSet = buffer.charAt(i) == '1';
            lonset.set(j++, isSet);
        }

        //odd bits
        j=0;
        for (int i=1; i< numbits*2;i+=2) {
            boolean isSet = false;
            if ( i < buffer.length() )
                isSet = buffer.charAt(i) == '1';
            latset.set(j++, isSet);
        }
        //中国地理坐标：东经73°至东经135°，北纬4°至北纬53°
        //这里使用世界范围.
        double lon = decode(lonset, -90, 90);
        double lat = decode(latset, -180, 180);

        return new double[] {lat, lon};
    }

    private static double decode(BitSet bs, double floor, double ceiling) {
        double mid = 0;
        for (int i=0; i<bs.length(); i++) {
            mid = (floor + ceiling) / 2;
            if (bs.get(i))
                floor = mid;
            else
                ceiling = mid;
        }
        return mid;
    }
}
