public void insert(int item, int position){
    if(first == null){
        addFirst(item);
        return;
    }
    IntNode ptr = first;
    int i = 0;
    for(i = 0; i < position; i++){
        if(ptr.next == null){
            break;
        }
        ptr = ptr.next
    }
    Intnode temp = new IntNode(item, ptr.next);
    ptr.next = temp;
}

public void reverse(){
    IntNode prev = null;
    IntNode cur = first;
    while(cur != null){
        IntNode temp = prev;
        prev = cur;
        cur = cur.next;
        prev.next = temp;
    }
    first = prev;
}
