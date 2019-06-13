package ternak.lele.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

public class GeneralHelper {
    public static String resultSetToString(ResultSet resultSet) {
        JSONArray jsonArray = new JSONArray();
        try {
            while (resultSet.next()) {
                int total_rows = resultSet.getMetaData().getColumnCount();
                for (int i = 0; i < total_rows; i++) {
                    JSONObject obj = new JSONObject();
                    obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                            .toLowerCase(), resultSet.getObject(i + 1));
                    jsonArray.put(obj);
                }
            }
        } catch (Exception e) {
        }
        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }

    public static boolean[] getBooleanArrayFromJson(JSONObject data, String key) {
        boolean[] result;
        try {
            JSONArray array = data.getJSONArray(key);
            int size = array.length();
            result = new boolean[size];
            for (int i = 0; i < size; i++) {
                result[i] = array.getBoolean(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public static JSONObject resultSetToJson(ResultSet resultSet) {
        JSONObject data;
        char[] res = resultSetToString(resultSet).toCharArray();
        res[0] = '{';
        res[res.length - 1] = '}';
        String result = new String(res);
        try {
            data = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public static boolean isNumeric(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean[] stringToBooleanArray(String s){
        s = s.substring(1, s.length()-1);
        String data[] = s.split(",");
        boolean[] res = new boolean[data.length];
        for(int i=0; i<data.length; i++){
            res[i] = data[i].equals("1") ? true : false;
        }
        return res;
    }

    public static String stringToMD5(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest);
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
