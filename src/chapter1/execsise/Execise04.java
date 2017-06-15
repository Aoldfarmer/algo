package chapter1.execsise;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import chapter1.entity.Node;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-14 2:59 PM
 */
public class Execise04 {

    public static final String SYS_PATH = "F:/learningProject/algo/src/chapter1/execsise/";

    public static final String INCLUDE = "#include ";

    public static final String FILE_NAME_SUFFIX = ".txt";

    public static List<Node> tree = new ArrayList<>();

    public static void readFile(String filePath) throws Exception {

        Node<String> pNode = null;
        for (Node node : tree) {
            if (node.getSelf().equals(filePath)) {
                pNode = node;
            }
        }
        if (pNode == null) {
            pNode = new Node<>(filePath);
        }
        tree.add(pNode);

        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath)));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (line.contains(INCLUDE)) {
                String newPath = SYS_PATH + line.substring(
                        line.indexOf(INCLUDE) + INCLUDE.length()) + FILE_NAME_SUFFIX;


                List<Node> pNodes = pNode.getParentTreeNodes();
                for (Node paNode : pNodes) {
                    if (paNode.getSelf().equals(newPath)) {
                        System.out.println("Error");
                        throw new Exception("Error");
                    }
                }


                Node<String> cNode = new Node<>(newPath);
                pNode.addChildTreeNode(cNode);
                cNode.setParent(pNode);
                tree.add(cNode);

                readFile(newPath);
            } else {
                System.out.println(line);
            }
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        readFile(SYS_PATH +"A.txt");
    }

}
