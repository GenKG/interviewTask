import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFile {
    public static void searchFile(String string) {
        try {
            List<File> files = Files.walk(Paths.get(string))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .sorted(Comparator.comparing(File::getName))
                    .collect(Collectors.toList());
            readFile(files);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void readFile(List<File> file)  {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for(File element: file) {
            try (FileInputStream fileInputStream = new FileInputStream(element)) {
                while (fileInputStream.available() > 0){
                    byteArrayOutputStream.write(fileInputStream.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try(FileOutputStream fileOutputStream = new FileOutputStream(file.get(0))) {
                byteArrayOutputStream.writeTo(fileOutputStream);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args)  {
        //указать путь директории
        searchFile("D://TestDirectory");
    }
}
