package xal.smf.impl;

import xal.smf.impl.qualify.ElementTypeManager;
import xal.tools.data.DataAdaptor;

public class EDipole extends Electrostatic {

    public static String      s_strType   = "EDipole";

    // orientation constants
    public final static int NO_ORIENTATION = 0;
    public final static int HORIZONTAL = 1;
    public final static int VERTICAL = 2;

	/** horizontal dipole type */
    public static final String HORIZONTAL_TYPE = "DHE";
	
	/** vertical dipole type */
    public static final String VERTICAL_TYPE = "DVE";
    
    static {
        registerType();
    }
    
    
    /*
     * Register type for qualification
     */
    private static void registerType() {
//        ElementTypeManager typeManager = ElementTypeManager.defaultManager();
//        typeManager.registerType(EQuad.class, "EQuad");
    }
    
    public EDipole(String strId) {
		super(strId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return s_strType;
	}

    /**
     * Get the orientation of the magnet as defined by MagnetType.  The orientation
     * of the quad is determined by its type: QH or QV
     * @return One of HORIZONTAL or VERTICAL
     */
	@Override
    public int getOrientation() {
    	return s_strType.equalsIgnoreCase( HORIZONTAL_TYPE ) ? HORIZONTAL : VERTICAL;
    }          
      
    /**
     * Update the instance with data from the data adaptor.  Overrides the default implementation to 
	 * set the quadrupole type since a quadrupole type can be either "QHE" or "QVE".
     * @param adaptor The data provider.
     */
    public void update( final DataAdaptor adaptor ) {
    	if ( adaptor.hasAttribute( "type" ) ) {
            s_strType = adaptor.stringValue( "type" );
        }
        super.update( adaptor );
        ElementTypeManager typeManager = ElementTypeManager.defaultManager();
        // check if this type already registered first.  If not, register it.
        if (!(typeManager.match(EQuad.class, s_strType)))
        	typeManager.registerType(EQuad.class, s_strType);
    }
    
    public boolean isKindOf( final String type ) {
        return type.equalsIgnoreCase( s_strType ) || super.isKindOf( type );
    }
}
