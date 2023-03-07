import java.util.ArrayList;

public class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(Integer val) {
            this.val = val;
        }

        TreeNode(Integer val, TreeNode left ) {
            this.val = val;
            this.left = left;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString() {
            return "TreeNode{" +
                    " VAL = " + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

    /**
     * Формирование массива порядка обхода дерева
     * @param root входящее дерево
     * @return массив порядка обхода дерева
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            if (root != null && root.val != null) {
                result.add(root.val);
                result.addAll(preorderTraversal(root.left));
                result.addAll(preorderTraversal(root.right));
            }
            return result;
    }
   
    /**
    * Печать порядка обхода дерева
    * @param array массив порядка обхода дерева
    */
    public void printOutOrder(ArrayList<Integer> array) {
        System.out.println();
        System.out.println("Порядок обхода дерева: " + array);
        StringBuilder stroka = new StringBuilder();
        for (Integer el : array) {
            stroka.append(el + " --> ");
        }
        stroka.append("финиш");
        System.out.println(stroka);
    }
        
    }

