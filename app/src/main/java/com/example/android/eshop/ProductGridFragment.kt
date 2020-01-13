package com.example.android.eshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.eshop.network.ProductEntry
import com.example.android.eshop.product.ProductCardRecyclerViewAdapter
import com.example.android.eshop.product.ProductGridItemDecoration
import com.example.android.eshop.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter
import kotlinx.android.synthetic.main.product_grid_fragment.view.*

const val DISPLAY_COLUMNS = 3

class ProductGridFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with the ProductGrid theme
        val view = inflater.inflate(R.layout.product_grid_fragment, container, false)

        // Set up the tool bar
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        }

        // Set up the RecyclerView
        view.recycler_view.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % DISPLAY_COLUMNS == 2) 2 else 1
            }
        }
        view.recycler_view.layoutManager = gridLayoutManager
        val adapter = StaggeredProductCardRecyclerViewAdapter(
            ProductEntry.initProductEntryList(resources))
        view.recycler_view.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.es_staggered_product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.es_staggered_product_grid_spacing_small)
        view.recycler_view.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
}
