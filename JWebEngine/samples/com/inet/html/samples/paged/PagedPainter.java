package com.inet.html.samples.paged;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import com.inet.html.ViewPainter;

/**
 * Painter to detect if an element won't be completely painted do the the lower clip bound 
 */
public class PagedPainter implements ViewPainter{
    
    private boolean wasPaint;
    private final PagedPainterFactory pagedPainterFactory;

    /**
     * Creates the painter
     * @param pagedPainterFactory the factory which created the painter, required as a callback
     */
    public PagedPainter( PagedPainterFactory pagedPainterFactory ) {
        this.pagedPainterFactory = pagedPainterFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean paint( Graphics g, Shape allocation ) {
        if(wasPaint){
            return false;
        }
        Rectangle rect = allocation.getBounds();
        if( rect.width == 0 || rect.height == 0 ){
            // ignore invisible elements
            return false;
        }
        Rectangle clip = g.getClipBounds();
        if( clip == null ){ 
            // if there is no clip, paint all elements
            wasPaint = true;
            return true;
        }
        if( rect.getMaxY() < clip.getMinY() ){ 
            // the element is above the clip area
            return false;
        }
        if( rect.getMaxY() > clip.getMaxY() && rect.y > clip.y ){ 
            // this element would be clipped; if it's at the top of the current page, paint it anyways
            // to avoid deadlocks for elements which are larger than a page
            pagedPainterFactory.notifyNotPaintedView( (int)rect.getMinY() );
            return false;
        }
        // this element fits into the page, mark as painted and allow it to be rendered
        wasPaint = rect.height < clip.height; // this is to paint 'too large' images across several pages
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new PagedPainter( pagedPainterFactory );
    }
}
