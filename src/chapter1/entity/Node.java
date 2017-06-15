package chapter1.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yunqiangdi
 * @version 1.0
 * @since 2017-06-14 6:33 PM
 */
public class Node<T> {

    private T self;
    private Node parent;
    private List<Node> child;


    public Node(T self) {
        initChildTreeNode();
        this.self = self;
    }


    public void initChildTreeNode() {
        if (null == child) {
            child = new ArrayList<>();
        }
    }


    public void addChildTreeNode(Node treeNode) {
        initChildTreeNode();
        child.add(treeNode);
    }


    public List<Node> getParentTreeNodes() {
        List<Node> parentTreeNodes = new ArrayList<>();
        Node parentTreeNode = this.getParent();
        if (null == parentTreeNode) {
            return parentTreeNodes;
        } else {
            parentTreeNodes.add(parentTreeNode);
            parentTreeNodes.addAll(parentTreeNode.getParentTreeNodes());
            return parentTreeNodes;
        }
    }

    public T getSelf() {
        return self;
    }

    public void setSelf(T self) {
        this.self = self;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChild() {
        return child;
    }

    public void setChild(List<Node> child) {
        this.child = child;
    }
}
