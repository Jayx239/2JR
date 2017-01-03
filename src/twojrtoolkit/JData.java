/**
 * JData abstract class
 */
package twojrtoolkit;

/**
 * @author Jason Gallagher
 *
 */
public abstract class JData extends JIdentity implements ISendable{
	public JData() {
		
	}
	
	public JData(int inId, String inName, int inSize) {
		super(inId,inName);
		size = inSize;
	}
	
	int size;
	int savings;
	
	abstract byte[] compress();
}
