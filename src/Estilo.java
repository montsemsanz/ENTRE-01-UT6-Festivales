public enum Estilo {
    HIPHOP, INDIE, POP, ROCK, FUSION, RAP, ELECTRONICA, PUNK, BLUES;

    public String toString(){
        switch (this){
            case HIPHOP: return "HIPHOP";
            case INDIE: return "INDIE";
            case POP: return "POP";
            case ROCK: return "ROCK";
            case FUSION: return "FUSION";
            case RAP: return "RAP";
            case ELECTRONICA: return "ELECTRONICA";
            case PUNK: return "PUNK";
            case BLUES: return "BLUES";
        }
        return null;
    }
}

