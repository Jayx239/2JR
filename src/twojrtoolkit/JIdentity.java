/**
 * JIdentity class
 * <p>
 * 
 */
package twojrtoolkit;

/**
 * @author Jason Gallagher
 *
 */
public abstract class JIdentity implements IPrintable {
	
	public JIdentity(){
		
	}
	
	public JIdentity(int inId, String inName){
		id = inId;
		name = inName;
	}
	
	int id;
	String name;
}
