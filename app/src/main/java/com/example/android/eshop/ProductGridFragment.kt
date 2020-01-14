package com.example.android.eshop

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.eshop.network.ProductEntry
import com.example.android.eshop.product.ProductGridItemDecoration
import com.example.android.eshop.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter
import com.example.android.eshop.util.NavigationIconClickListener
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
        view.app_bar.setNavigationOnClickListener(NavigationIconClickListener(
            activity!!,
            view.product_grid,
            AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(context!!, R.drawable.es_branded_menu), // Menu open icon
            ContextCompat.getDrawable(context!!, R.drawable.es_close_menu))) // Menu close icon

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.product_grid.background = context?.getDrawable(R.drawable.es_product_grid_background_shape)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when (item.itemId) {
            R.id.search -> {
                // open search
                Toast.makeText(activity, "Search", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.filter -> {
                // open search
                Toast.makeText(activity, "Filter", Toast.LENGTH_SHORT).show()
                activity?.let{
                    val intent = Intent (it, FiltersActivity::class.java)
                    it.startActivity(intent)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        })
    }
}
