/**
 * 
 */
package twojrtoolkit;

import java.nio.ByteBuffer;

/**
 * @author Jason Gallagher
 *
 */
public class JDouble extends JData{

	JDouble(int inId, String inName, int inSize, double inValue) {
		super(inId, inName, inSize);
		value = inValue;
	}
	
	private int savings;
	private double value;
	private final int doubleLengthInBytes = 8;
	
	@Override
	public byte[] toByte() {
		byte[] byteArray = new byte[doubleLengthInBytes];
		ByteBuffer.wrap(byteArray).putDouble(value);
		return byteArray;
	}

	@Override
	public String print() {
		System.out.println(value);
		return Double.toString(value);
	}

	@Override
	byte[] compress() {
		// TODO Auto-generated method stub
		return null;
	}

}
