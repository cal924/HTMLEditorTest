package com.inet.html.samples.paged;

import com.inet.html.PainterFactory;
import com.inet.html.ViewPainter;

/**
 * A PainterFactory to detect the first element which was clipped by the lower clip bound
 */
public class PagedPainterFactory implements PainterFactory{

    private int minNotPainted = Integer.MAX_VALUE;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ViewPainter createInstance() {
        return new PagedPainter( this );
    }

    /**
     * Called by the painter to notify the factory of the upper bound of an element which did not
     * fit into the clip.  
     * @param yOffset the upper bound of an element which did not
     * fit into the clip. This value MUST be greater than the upper bound of the clip
     */
    public void notifyNotPaintedView( int yOffset ){
        minNotPainted = Math.min( yOffset, minNotPainted );
    }
    
    /**
     * Reset the y offset for another page
     */
    public void reset(){
        minNotPainted = Integer.MAX_VALUE;
    }
    
    /**
     * Returns the yOffset for the clip of the next page to be rendered. If it returns {@link Integer#MAX_VALUE}
     * than there was no element which hit the lower clip bound. So this was either the last page or
     * Incidentally there no element which hit this bound. 
     * @return the yOffset for the clip of the next page to be rendered.
     */
    public int getNextYOffset(){
        return minNotPainted;
    }
}
