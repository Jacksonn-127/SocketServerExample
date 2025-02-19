import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalTime;

public class ClientHandler implements Runnable {

	private final int numTasks = 2;

	private Socket soc = null;

	public ClientHandler(Socket soc) throws SocketException {
		if (soc == null)
			throw new IllegalArgumentException("null socket argument in ClientHandler constructor");
		if (!soc.isConnected())
			throw new SocketException("socket is not connected");
		this.soc = soc;
	}

	@Override
	public void run() {
		DataInputStream in = null;
		try {
			in = new DataInputStream(soc.getInputStream());
			String line = in.readUTF();
			if (line.length() == 0)
				throw new IOException("empty string from client");
			String result = performOp(line);
			if (result.length() != 0) {
				DataOutputStream out = new DataOutputStream(soc.getOutputStream());
				out.writeUTF(result);
				out.close();
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (InvalidTaskNumException e) {
			System.err.println(e.getMessage());
		} catch (JobException e) {
			System.err.println(e.getMessage());
		}

	}


	private boolean isValidTaskNum(Integer num) {
		return 1 <= num && num <= numTasks;
	}

	private String performOp(String line) throws InvalidTaskNumException, JobException {
		String result = null;
		if (line == null || line.length() == 0)
			throw new IllegalArgumentException("no operation to perform");
		String[] tokens = line.split(" ");
		int taskNum = Integer.parseInt(tokens[0]); // throws NumberFormatException
		if (!isValidTaskNum(taskNum))
			throw new InvalidTaskNumException("invalid task number");
		if(taskNum == 1)
			result = quadraticEquation(tokens);
		if (taskNum == 2)
			result = palindrome(tokens);

		return result;
	}

	private String quadraticEquation(String[] tokens) throws JobException{
		if (tokens == null)
			throw new IllegalArgumentException("null tokens argument in square");
		if (tokens.length != 2)
			throw new JobException("invalid number of arguments");

		double a, b, c;

		try {
			String temp = tokens[1];

			// split the input string, for my input instead of reading 3 times from args I split each double value with a "!" character
			String[] nums = temp.split("!");

			a = Double.parseDouble(nums[0]);
			b = Double.parseDouble(nums[1]);
			c = Double.parseDouble(nums[2]);

		} catch (NumberFormatException e) {
			throw new JobException("illegal argument in quadratic equation: " + e.getMessage());
		}

		// edge case
		if(a == 0){
			throw new JobException("Coefficient a cannot be 0 in quadratic equation");
		}

		// find determinant and declare variables
		double determinant = b * b - 4 * a * c;
		double rootOne, rootTwo;


		// if else case to determine how I should determine what to return
		if(determinant < 0){
			return "No real roots";
		}
		else if(determinant == 0){
			rootOne = -b / (2 * a);
			return "There is one root: " + rootOne;
		}
		else{
			rootOne = (-b + Math.sqrt(determinant)) / (2 * a);
			rootTwo = (-b - Math.sqrt(determinant)) / (2 * a);
			return ("Roots are " + rootOne + ", " +rootTwo);
		}
	}


	private String palindrome(String[] tokens) throws JobException {
		String logic = "true";

		// if tokens are null or tokens have other than 2 arguments throw error
		if (tokens == null)
			throw new IllegalArgumentException("null tokens argument in square");
		if (tokens.length != 2)
			throw new JobException("invalid number of arguments");

		String word;

		try {
			word = tokens[1];
		} catch (Exception e) {
			throw new JobException("Unexpected error in palindrome " + e.getMessage());
		}

		// edge cases where length is shorter than generic algorithm can best be preformed
		if (word.length() <= 2) {
			switch (word.length()) {
				case 0:
					return "false";
				case 1:
					return "true";
				case 2: {
					if (word.charAt(0) == word.charAt(1)) {
						return "true";
					}
					return "false";
				}
			}
		}

		// double pointer algorithm n/2 time
		for (int i = 0; i < word.length() / 2; i++) {
			if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
				logic = "false";
				break;
			}
		}

		return logic;
	}
}

