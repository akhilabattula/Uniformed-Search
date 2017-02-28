package original_ai;

class Join{
    public final int cost;
    public final Ucsnode target;

    public Join(Ucsnode targetUcsnode, int costVal){
        cost = costVal;
        target = targetUcsnode;

    }
}