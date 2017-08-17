package dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.BoardGame;

public class DomParser {
	public static void main(String[] args) {
		List<BoardGame> games = readBoardGames("src/main/resources/boardgames.xml");

		System.out.println(games);

		writeBoardGames("src/main/resources/boardgames-backup.xml", games);
	}

	private static List<BoardGame> readBoardGames(String filename) {
		List<BoardGame> games = new ArrayList<BoardGame>();

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder docbuilder = docFactory.newDocumentBuilder();
			Document doc = docbuilder.parse(filename);
			doc.getDocumentElement().normalize();

			System.out.println(doc.getDocumentElement().getNodeName());
			NodeList boardGames = doc.getElementsByTagName("board-game");
			for (int i = 0; i < boardGames.getLength(); i++) {
				Element game = (Element) boardGames.item(i);
				BoardGame bg = new BoardGame();
				bg.setId(Integer.parseInt(game.getAttribute("id")));
				bg.setTitle(game.getElementsByTagName("title").item(0).getTextContent());
				bg.setDuration(Double.parseDouble(game.getElementsByTagName("duration").item(0).getTextContent()));
				bg.setMaxPlayers(Integer.parseInt(game.getElementsByTagName("max-players").item(0).getTextContent()));
				games.add(bg);
				// NamedNodeMap attributes = game.getAttributes();
				// System.out.println("\t" + game.getNodeName() + ": " +
				// attributes.getNamedItem("id"));
				// BoardGame bg = new BoardGame();
				// bg.setId(Integer.parseInt(attributes.getNamedItem("id").getTextContent()));
				//
				// NodeList boardGameChildren = game.getChildNodes();
				// for (int j = 0; j < boardGameChildren.getLength(); j++) {
				// Node child = boardGameChildren.item(j);
				// if (child.getNodeType() == Node.ELEMENT_NODE) {
				// System.out.println("\t\t" + child.getNodeName() + ": " +
				// child.getTextContent());
				// if ("title".equals(child.getNodeName())) {
				// bg.setTitle(child.getTextContent());
				// } else if ("duration".equals(child.getNodeName())) {
				// bg.setDuration(Double.parseDouble(child.getTextContent()));
				// } else if ("max-players".equals(child.getNodeName())) {
				// bg.setMaxPlayers(Double);
				// }
				// }
				// }
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return games;
	}

	private static void writeBoardGames(String filename, List<BoardGame> games) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element root = doc.createElement("board-games");
			doc.appendChild(root);

			for (BoardGame b : games) {
				Element game = doc.createElement("board-game");

				game.setAttribute("id", "" + b.getId());

				Element title = doc.createElement("title");
				title.appendChild(doc.createTextNode(b.getTitle()));
				game.appendChild(title);

				Element duration = doc.createElement("duration");
				duration.appendChild(doc.createTextNode("" + b.getDuration()));
				game.appendChild(duration);

				Element maxPlayers = doc.createElement("max-players");
				maxPlayers.appendChild(doc.createTextNode("" + b.getMaxPlayers()));
				game.appendChild(maxPlayers);

				root.appendChild(game);

			}

			TransformerFactory tf = TransformerFactory.newInstance();

			Transformer optimusPrime = tf.newTransformer();
			optimusPrime.setOutputProperty(OutputKeys.INDENT, "yes");
			optimusPrime.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));

			optimusPrime.transform(source, result); // and roll out

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
