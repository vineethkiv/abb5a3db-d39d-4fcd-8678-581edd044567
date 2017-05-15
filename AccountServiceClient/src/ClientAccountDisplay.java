
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vineeth Kiv
 * Simple client class to invoke Account service
 *
 */
public class ClientAccountDisplay {

	private static Map<String, String> jobProperties;

	public static void main(String[] args) throws Exception {
		loadProperties(args);
		String loginToken = getJWTToken();
		if (null != loginToken && !"".equals(loginToken)) {
			printAccountDetails(loginToken);
		} else {
			System.out.println("ClientAccountDisplay.main(): Invalid Token");
		}

	}

	/**
	 * @return
	 * Method to get the login token
	 */
	private static String getJWTToken() {

		URL url;
		HttpURLConnection urlConnection = null;
		String token = "";
		try {
			url = new URL("http://" + jobProperties.get("host") + ":" + jobProperties.get("port") + "/login");
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.getOutputStream()
					.write("{\"username\":\"account-service-admin\",\"password\":\"12345678\"}".getBytes());

			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + urlConnection.getResponseCode());
			}

			Map<String, List<String>> map = urlConnection.getHeaderFields();
			token = map.get("Authorization").get(0).substring(7);
		} catch (Exception e) {
			System.err.println("ClientAccountDisplay.getJWTToken() : " + e.getMessage());
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return token;
	}

	/**
	 * @param token
	 * Method to print account service response
	 */
	public static void printAccountDetails(String token) {

		URL url;
		HttpURLConnection urlConnection = null;
		try {
			url = new URL("http://" + jobProperties.get("host") + ":" + jobProperties.get("port")
					+ "/accounts/getAccountById/" + jobProperties.get("accountId"));
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Authorization", token);

			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Account Details not available for " + jobProperties.get("accountId"));
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}
	
	private static void loadProperties(String[] args) {
		jobProperties = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].trim().equals("-ip")) {
				jobProperties.put("host", args[i + 1]);
			}
			if (args[i].trim().equals("-port")) {
				jobProperties.put("port", args[i + 1]);
			}
			if (args[i].trim().equals("-accountID")) {
				jobProperties.put("accountId", args[i + 1]);
			}

		}
	}

}
