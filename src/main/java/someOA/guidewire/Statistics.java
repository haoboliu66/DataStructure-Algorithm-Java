package src.main.java.someOA.guidewire;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    /*
    String S = "\"music 1011b\n" +
                "images 0b\n" +
                "movies 10200b\n" +
                "other 105b\"";
     */
    public static void main(String[] args) {
    /*
    "my.song.mp3 11b
greatSong.flac 1000b
not3.txt 5b
video.mp4 200b
game.exe 100b
mov!e.mkv 10000b"
     */
        String s = "\"my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "mov!e.mkv 10000b";
        solution(s);

        System.out.println(add("100b", "1000b"));
    }

    static Map<String, String> typeMap = new HashMap<>();
    static final String MUSIC = "music";
    static final String IMAGE = "image";
    static final String MOVIE = "movie";
    static final String OTHER = "other";

    public static String solution(String s) {
        String[] lines = s.split("\\n");
        for (String line : lines) {
            String[] fileNameAndSize = line.split(" ");
            String fileName = fileNameAndSize[0];
            String fileSize = fileNameAndSize[1];
            String curType;
            if(isMusic(fileName)){
                curType = MUSIC;
            }else if(isImage(fileName)){
                curType = IMAGE;
            }else if(isMovie(fileName)){
                curType = MOVIE;
            }else{
                curType = OTHER;
            }
            if(!typeMap.containsKey(curType)){
                typeMap.put(curType, fileSize);
            }else{
                typeMap.put(curType,add(fileSize, add(fileSize, typeMap.get(curType))));
            }
        }
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            res.append(entry.getKey()).append(entry.getValue()).append("\\n");
        }

        return res.toString();
    }

    public static String add(String size1, String size2){
        int s1 = Integer.parseInt(size1.substring(0, size1.length() - 1));
        int s2 = Integer.parseInt(size2.substring(0, size2.length() - 1));
        return (s1 + s2) + "b";
    }

    public static boolean isMusic(String fileName) {
        String reg = ".+(.mp3|.aac|.flac)$";
        return fileName.matches(reg);
    }
    public static boolean isImage(String fileName) {
        String reg = ".+(.jpg|.bmp|.gif)$";
        return fileName.matches(reg);
    }
    public static boolean isMovie(String fileName) {
        String reg = ".+(.mp4|.avi|.mkv)$";
        return fileName.matches(reg);
    }

}
