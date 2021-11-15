import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.nio.charset.*;
import java.net.*;

public class Detectar_Face_Em_Java{
    class Program{
        public static IFaceClient Authenticate(string endpoint, string key){
            return new FaceClient(ApiKeyServiceClientCredentials(key)){ Endpoint = endpoint };
        }
        public static async Task DetectFaceExtract(IFaceClient client, string url, string recognitionModel){
            Console.WriteLine("Detectção Facial");
            Console.WriteLine();
            IList<DetectedFace> detectedFaces;
            detectedFaces = await client.Face.DetectWithUrlAsync("{url}", returnFaceAttributes: new List<FaceAttributeType> {FaceAttributeType.Age}, recognitionModel: recognitionModel);
            Console.WriteLine(detectedFaces.Count + " rostos detectados");
            Console.WriteLine();
            foreach(var face in detectedFaces){
                Console.WriteLine("Atributos do Rosto");
                Console.WriteLine(" Idade :" + FaceAttributeType.Age);//Imprime a idade do rosto;
                Console.WriteLine();
            }
        }
    }
    /*** Código que inicia o programa;*/
    public static void main(String[] args){
        String RECOGNITION_MODEL2 = RecognitionModel.Recognition02;
        String RECOGNITION_MODEL1 = RecognitionModel.Recognition01;
        String url_do_video;
        IFaceClient client = Authenticate("https://detectarrostoexercicio4.cognitiveservices.azure.com/", "340b5c20191d4fdfaf75e48e6c123e36");
        Console.Clear();
        url_do_video = "https://imagens.canaltech.com.br/235861.470219-StyleGAN.jpg";//Acessa a imagem;
        DetectFaceExtract(client, url_do_video, RECOGNITION_MODEL2).Wait();//Detecção Facial;
        Console.ReadLine();
        Console.Clear();
    }
}