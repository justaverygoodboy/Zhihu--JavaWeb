package utils;
import org.json.*;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Rs2Json {
    public String resultSetToJson(ResultSet rs) throws SQLException, UnsupportedEncodingException, JSONException {
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String value = null;
                String columnName = metaData.getColumnLabel(i);//列名称
                if (rs.getString(columnName) != null && !rs.getString(columnName).equals("")) {
                    value = new String(rs.getBytes(columnName), "UTF-8");//列的值,有数据则转码
                } else {
                    value = "";//列的值，为空，直接取出去
                }
                jsonObj.put(columnName, value);
            }
            array.put(jsonObj);
        }
        rs.close();
        return array.toString();
    }
}
