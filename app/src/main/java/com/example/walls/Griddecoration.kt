package com.example.walls

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridDecoration(spancount: Int, Spacing: Int, IncludeEdge: Boolean) :
    RecyclerView.ItemDecoration() {
      private var spanCount:Int = spancount;
      private var spacing:Int = Spacing;
      private var includeEdge= IncludeEdge;

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        if(includeEdge){
            outRect.left = spacing -column *spacing/spanCount
            outRect.right = (column+1) *spacing/spanCount
            if(position<spanCount){
                outRect.top =spacing
            }
            outRect.bottom = spacing
        }else{
            outRect.left = column * spacing/spanCount
            outRect.right = spacing - (column + 1) *spacing/spanCount
            if(position>=spanCount){
                outRect.top = spacing
            }
        }
    }

}