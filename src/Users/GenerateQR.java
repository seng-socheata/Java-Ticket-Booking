import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class GenerateQR {

    public static void generateQRCode(String text) {
        int size = 20; // Adjust size to fit terminal
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size);

            // Print QR code to terminal using ASCII characters
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    System.out.print(bitMatrix.get(x, y) ? "██" : "  "); // Dark blocks for QR
                }
                System.out.println();
            }

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String textInput = "";
        generateQRCode(textInput);
    }
}
