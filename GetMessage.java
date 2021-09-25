import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The Class GetMessage is used to get the request from the server.
 */
public class GetMessage {

	/**
	 * Send GET query and add the decoded text to the URL.
	 *
	 * @param s the s
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String sendGETQuery(String s) throws IOException {
		String decoded = s;
		URL obj = new URL("http://cswebcat.swan.ac.uk/message?solution=" + decoded);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		String message = null;
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();

		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			System.out.println("Message of the day");
			message = response.toString();
		} else {
			System.out.println("GET request not worked");
		}
		return message;

	}

	/**
	 * Send GET to get the code from the server.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String sendGET() throws IOException {
		URL obj = new URL("http://cswebcat.swan.ac.uk/puzzle");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		String code = null;
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();

		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			code = response.toString();
		} else {
			System.out.println("GET request not worked");
		}
		return code;

	}

	/**
	 * Decode this decode the code f.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String decode(String s) {
		s = s.toUpperCase();
		String result;
		char[] temp = s.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if ((i % 2) != 1) {
				switch (temp[i]) {
				case 'Z':
					temp[i] = (char) 65;
					break;
				default:
					temp[i] = (char) ((int) temp[i] + 1);
					break;
				}
			} else {
				switch (temp[i]) {
				case 'A':
					temp[i] = (char) 90;
					break;
				default:
					temp[i] = (char) ((int) temp[i] - 1);
					break;
				}
			}
		}
		result = new String(temp);
		System.out.println("decoded code");
		System.out.println(result);
		return result;
	}
}
