package be.genealogie.domein.dto;

public enum MediaType {
    JPG, PNG;

    public static MediaType forFilename(String name) {
        if(name.toLowerCase().endsWith(".jpg")){
            return JPG;
        }else if(name.toLowerCase().endsWith(".png")){
            return PNG;
        }
        return JPG;

    }
}
