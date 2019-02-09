import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Scan {
	public static void main(String args[]) {
		new Scan();
	}

	public Scan() {
		String output = "Loaded QR code";
		String path = "code.png";
		String charset = "UTF-8";
		Map map = new HashMap();
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		try {
			encript(output, path, charset, map, 200, 200);
			System.out.println("No errors occured...");
			System.out.println(decript(path, charset, map));
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void encript(String output, String file, String charset, Map hintMap, int width, int height)
			throws WriterException, IOException {
		BitMatrix mat = new MultiFormatWriter().encode(new String(output.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);
		MatrixToImageWriter.writeToFile(mat, file.substring(file.lastIndexOf('.') + 1), new File(file));
	}

	public String decript(String file, String charset, Map map) throws IOException, NotFoundException {
		BinaryBitmap binBit = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new File(file)))));
		Result result = new MultiFormatReader().decode(binBit, (Hashtable) map);
		return result.getText();
	}
}
