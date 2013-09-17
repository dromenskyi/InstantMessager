package client.thread;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import util.xml.*;

public class OutputThread implements Runnable {

	private BufferedReader input;
	private DataOutputStream output;
	
	public OutputThread(DataOutputStream out, BufferedReader in) throws IOException {
		output = out;		
		input = in;
	}
	
	public void run() {
		try {
			send();
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "Problems with a server");
		} catch (ParserConfigurationException parserException) {
			parserException.printStackTrace();
		} catch (TransformerConfigurationException transformerConfException) {
			transformerConfException.printStackTrace();
		} catch (TransformerException transformerException) {
			transformerException.printStackTrace();
		} catch (ParseException parseException) {
			parseException.printStackTrace();
		} catch (SAXException saxException) {
			saxException.printStackTrace();
		}
	}
	
	private void send() throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, ParseException, SAXException {
		while (true) {
			String from = input.readLine();
			String to = input.readLine();
			String text = input.readLine();
			Date time = new Date();
			
			Message message = new Message(time, from, to, text);
			
			Operations.sendMessage(message, output);
		}
	}
}