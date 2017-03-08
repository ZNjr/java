import java.util.Vector;

 public class Mail {

    public enum Format{PLAIN, HTML}
    private String message;
    private Format format;
    private Vector<Attachment> attachments;
    public Mail(String message, Format format) {
        this.message = message;
        this.format = format;
    }
    public Format getFormat(){
        return format;
    }
    public String toString(){
    if(format == Format.HTML){
       return "<html>\n <body>"+message+"</body>\n</html>";
    }else{
        return message;
        }
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setFormat(Format format){
        this.format = format;
    }
    public boolean addAttachment(Attachment attachment){
        if(attachment.getRozmiar() <= MAX_MAIL_SIZE){
            attachments.add((attachment));
            return true;
        }else{
            return false;
        }
    }
    public Attachment getAttachment(int index){
        return attachments.elementAt(index);
    }
    public void removeAttachment(String filename){
        for(int i=0;i<attachments.size();i++){
            if(attachments.elementAt(i).getFilename().equals(filename))
                attachments.remove(i);
        }
    }
    static int MAX_MAIL_SIZE = 100000;
}


