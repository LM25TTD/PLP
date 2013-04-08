package webcontrollers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.tree.DefaultMutableTreeNode;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import parser.EarleyAgent;
import parser.MalformedGrammarException;

@Controller("ShowTreePage")
@Scope("session")
public class ShowTreePage implements Serializable {

	private static final long serialVersionUID = -9222946791337573013L;
	private TreeNode noRaiz;
	private String expression;


	public TreeNode getNoRaiz() {
		return noRaiz;
	}

	public void setNoRaiz(TreeNode noRaiz) {
		this.noRaiz = noRaiz;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String parserExpression() {

		try {

			EarleyAgent proofOfSuccess = EarleyAgent.getEarleyAgent(expression);
			if (proofOfSuccess != null) {
				DefaultMutableTreeNode rootNode = EarleyAgent
						.parseTree(proofOfSuccess);
				noRaiz = newNodeWithChildren(rootNode, null);
				
			} else {
				
				noRaiz = null;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
								"A expressão não é válida!"));

			}
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (MalformedGrammarException e) {
			e.printStackTrace();
		}

		return null;
	}

	public TreeNode newNodeWithChildren(javax.swing.tree.TreeNode ttParent,
			TreeNode parent) {
		TreeNode newNode = new DefaultTreeNode(ttParent, parent);
		newNode.setExpanded(true);

		for (int i = 0; i < ttParent.getChildCount(); i++) {
			javax.swing.tree.TreeNode tt = ttParent.getChildAt(i);
			TreeNode newNode2 = newNodeWithChildren(tt, newNode);
			newNode2.setExpanded(true);
		}
		return newNode;
	}

}
