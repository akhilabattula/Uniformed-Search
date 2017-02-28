package original_ai;

class Ucsnode{

    public String value;
    public int pathCost;
    public Join[] adjacencies;
    public Ucsnode parent;
    public Boolean visit=false;

    public Ucsnode(String val){

        value = val;

    }

    public String toString(){
        return value;
    }

}

