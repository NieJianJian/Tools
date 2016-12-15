package njj.com.myapplication1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jian on 2016/12/15.
 */
public class HttpUtil {

    /**
     * @param address
     * @param listener
     * @return 将String 改为 void。因为网络请求是耗时操作，我们需要在子线程中执行，
     * 但是子线程无法通过return语句来返回数据，也不能在子线程中更新UI，所以利用回调来实现
     * 除非使用runOnUiThread()方法。
     */
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            if (listener != null) {
                listener.onFinish(response.toString());
            }
            /*return response.toString();*/
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onError(e);
            }
            /*return e.getMessage();*/
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }

}
