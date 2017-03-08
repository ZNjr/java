public class Attachment {
    private String filename;
    private int rozmiar;
   Attachment(String filename,int rozmiar){
       this.filename = filename;
       this.rozmiar = rozmiar;
   }

    public String getFilename(){
        return filename;
    }
    public int getRozmiar(){
        return rozmiar;
    }
    public String toString(){
        return "nazwa pliku: "+filename+" rozmiar: "+rozmiar;
    }
}

