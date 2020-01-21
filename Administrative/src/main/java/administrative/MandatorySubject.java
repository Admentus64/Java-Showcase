/*
 * Author: Robert Willem Hallink
 * Created: 28 November, 2019
 * Java, Maven Project
 * Open JDK 13.0.1, Eclipse 2019-09 4.13.0, Default Settings
 */



// Package
package administrative;



// Imports
import administrative.School;
import administrative.Subject;



// Public Child Class
public class MandatorySubject extends Subject {   // Start Class: MandatorySubject
	
	// Constructor
	public MandatorySubject(School school, String name) {   // Start Constructor: MandatorySubject
		super(school, name);
	} // End Constructor: MandatorySubject
	
	
	
	// Public Methods
	@Override
	public boolean isMandatory()						{ return true; }						// Method: isMandatory		(Return false, the subject is mandatory)
	
	// Extend class with more "mandatory" methods
	
} // End Class: MandatorySubject
