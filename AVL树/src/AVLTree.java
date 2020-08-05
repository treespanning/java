
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//实现纯key模型的AVL树
//要实现key-value模型，再结点种多保存一个value即可
public class AVLTree {
    /*记录树的根节点，如果是空树，则root==null*/
    private Node root=null;


    /*
    *插入AVl树
    * key为要插入的关键字
    * RuntimeException如果key重复了则插入失败
    *  */
    public void insert(int key){
        if(root==null){
            root=new Node(key,null);
            return;
        }

        Node parent=null;
        Node cur=root;
        while (cur!=null){
            if(key==cur.key){
                throw new RuntimeException("key:"+key+"，+"+"key已经存在，插入失败");
            }else if(key<cur.key){
                parent=cur;
                cur=cur.left;
            }else{
                parent=cur;
                cur=cur.right;
            }
        }

        //直到找到null的位置，才真正开始插入
        if(key<parent.key){
            cur=parent.left=new Node(key,parent);
        }else{
            //这里只能是key>parent.key,因为上面排查过相等
            cur=parent.right=new Node(key,parent);
        }
        //以上为正常搜索树插入过程
        //parent是要调整BF的结点
        //cur为破坏源所在位置的根结点

        while (true){
            if(cur==parent.left){
                parent.df++;
            }else{
                //cur=parent.right;
                parent.df--;
            }

            //分情况处理
            if(parent.df==0){
                break;
            }else if(parent.df==2){
                //调整，进行失衡修复
                //左左or左右
                LTree(cur,parent);
                break;
            }else if(parent.df==-2){
                //调整，进行失衡修复
                //右右or右左
                RTree(cur,parent);
                break;
            }else if(parent==root){
                //已经到根
                break;
            }

            //如果需要继续
            Node parentOfParent=parent.parent;
            cur=parent;
            parent=parentOfParent;
        }
    }
    public void LTree(Node cur,Node parent){
        if(cur.df==1){
            //左左
            fixLLLoseBalence(parent);
        }else{
            //-1
            //左右
            fixLRLoseBalence(parent);
        }
    }

    public void RTree(Node cur,Node parent){
        if(cur.df==-1){
            //右右
            fixRRLoseBalence(parent);
        }else{
            //1
            //右左
            fixRLLoseBalence(parent);
        }
    }


    /*
    * 在AVL树中查找对应的key
    * key为要查找的关键字
    * AVL是否包含这个key*/
    public boolean contains(int key){
        Node cur=root;
        while (cur!=null){
            if(key==cur.key){
                return true;
            }else if(key<cur.key){
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }
        return false;
    }


    //验证
    public void verify(){
        List<Integer> inOderList=new ArrayList<>();
        //计算该树的中序遍历
        inOrder(inOderList,root);
        //判断其是否有序
        //把得到中序序列排序，如果排序后和原来结果一样，说明原来有序
        List<Integer> inOrderListCopy=new ArrayList<>(inOderList);
        Collections.sort(inOrderListCopy);

        if(!inOrderListCopy.equals(inOderList)){
            throw new RuntimeException("AVL树不满足：中序遍历无序");
        }
        System.out.println("中序有序");
        
        //验证每个结点的BF是否计算正确
        preOrderAndCalcBF(root);
        System.out.println("BF正确性 验证成功" );

        //验证每个结点的BF是否都是（-1，0，1）
        preOrderVerifyBF(root);
        System.out.println("BF满足AVL树特征");
    }

    private void preOrderVerifyBF(Node node) {
        if(node!=null){
            if(node.df!=-1&&node.df!=0&&node.df!=1){
                throw new RuntimeException("结点："+node+" 的BF大小有误");
            }
            preOrderVerifyBF(node.left);
            preOrderVerifyBF(node.right);
        }
    }

    //结点高度
    private static int height(Node node){
        if(node==null){
            return 0;
        }
        int left=height(node.left);
        int right=height(node.right);
        return Math.max(left,right)+1;
    }

    //前序遍历求BF
    private void preOrderAndCalcBF(Node node) {
        if(node!=null){
            int left=height(node.left);
            int right=height(node.right);
            if(left-right!=node.df){
                throw new RuntimeException("结点："+node+" 的BF计算有误");
            }
            preOrderAndCalcBF(node.left);
            preOrderAndCalcBF(node.right);
        }
    }


    private static void inOrder(List<Integer> list,Node node){
        if(node!=null){
            inOrder(list,node.left);
            //处理node
            list.add(node.key);
            inOrder(list,node.right);
        }
    }


    //左旋
    private void leftRotate(Node parent){
        //如果前面都正确且走到这个位置，说明parent不是null；
        //并且parent.right也一定不是null

        Node parentParent=parent.parent;
        Node rightOfParent =parent.right;
        Node leftOfRightOfParent =rightOfParent.left;

        rightOfParent.parent=parentParent;
        //需要明确原来parent是parentOfParent的左结点还是右结点
        if(parentParent==null){
            //原来的根是parent
            //现在的根是rightOfParent
            root=rightOfParent;
        }
        else if(parent==parentParent.left){
            parentParent.left=rightOfParent;
        }else{
            parentParent.right=rightOfParent;
        }

        rightOfParent.left=parent;
        parent.parent=rightOfParent;

        parent.right=leftOfRightOfParent;
        if(leftOfRightOfParent!=null) {
            leftOfRightOfParent.parent = parent;
        }

    }

    //右旋
    private void rightRotate(Node parent){
        //如果前面都正确且走到这个位置，说明parent不是null；
        //并且parent.left也一定不是null
        Node parentOfParent=parent.parent;
        Node leftOfParent=parent.left;
        Node rightOfLeftOfParent=leftOfParent.right;

        leftOfParent.parent=parentOfParent;
        //需要明确原来parent是parentOfParent的左结点还是右结点
        if(parentOfParent==null){
            //原来的根是parent
            //现在的根是leftOfParent
            root=leftOfParent;
        }
        else if(parent==parentOfParent.left){
            //为失衡结点父节点的左子树
            parentOfParent.left=leftOfParent;
        }else{
            parentOfParent.right=leftOfParent;
        }

        leftOfParent.right=parent;
        parent.parent=leftOfParent;

        parent.left=rightOfLeftOfParent;
        if(rightOfLeftOfParent!=null){
            rightOfLeftOfParent.parent=parent;
        }
    }

    //左左失衡
    //左左失衡；失衡结点直接右旋
    private void fixLLLoseBalence(Node parent) {
        Node node=parent;//失衡结点
        Node leftOfNode =parent.left;
        rightRotate(parent);
        //根据计算结果，更新BF
        node.df=leftOfNode.df=0;
    }

    //左右失衡
    //失衡结点的左结点先左旋，失衡结点右旋
    private void fixLRLoseBalence(Node parent) {
        Node node=parent;//失衡结点
        Node leftOfNode =parent.left;//失衡结点左结点
        Node rightOfLeftOfNode=leftOfNode.right;

        leftRotate(leftOfNode);//失衡结点的左结点先左旋

        rightRotate(parent);//失衡结点右旋

        //根据计算结果，更新BF
        if(rightOfLeftOfNode.df==1){
            node.df=-1;
            leftOfNode.df=0;
            rightOfLeftOfNode.df=0;
        }else if(rightOfLeftOfNode.df==-1){
            node.df=0;
            leftOfNode.df=1;
            rightOfLeftOfNode.df=0;
        }else{
            node.df=leftOfNode.df=rightOfLeftOfNode.df=0;
        }
    }

    //右右失衡
    //失衡结点直接左旋
    private void fixRRLoseBalence(Node parent) {
        //Node node=parent;//失衡结点
        Node rightOfNode =parent.right;

        leftRotate(parent);
        //根据计算结果，更新BF
        parent.df=rightOfNode.df=0;
    }

    //右左失衡
    //失衡结点的右结点先右旋，失衡结点左旋
    private void fixRLLoseBalence(Node parent) {
        //Node node = parent;
        Node rightOfNode=parent.right;
        Node leftOfRightOfNode =rightOfNode.left;
        //失衡结点的右结点先右旋
        rightRotate(rightOfNode);

        //失衡结点左旋
        leftRotate(parent);

        //根据计算结果，更新BF
        if(leftOfRightOfNode.df==-1){
            //-1
            parent.df=1;
            rightOfNode.df=0;
            leftOfRightOfNode.df=0;
        }else if(leftOfRightOfNode.df==1){
            parent.df=0;
            rightOfNode.df=-1;
            leftOfRightOfNode.df=0;
        }else{
            //0
            parent.df=rightOfNode.df=leftOfRightOfNode.df=0;
        }
    }

}
