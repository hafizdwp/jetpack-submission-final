package me.hafizdwp.jetpack_submission_final.ui.detail

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_activity.*
import me.hafizdwp.jetpack_submission_final.R
import me.hafizdwp.jetpack_submission_final.base.BaseActivity
import me.hafizdwp.jetpack_submission_final.data.Const
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.ui.favorite.FavoriteFragment
import me.hafizdwp.jetpack_submission_final.utils.obtainViewModel
import me.hafizdwp.jetpack_submission_final.utils.toastSpammable
import me.hafizdwp.jetpack_submission_final.utils.withLoadingPlaceholder

/**
 * @author hafizdwp
 * 02/12/2019
 **/
class DetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_MOVREAK_DATA = "extra_data"

        fun startActivity(context: Context,
                          data: Movreak) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVREAK_DATA, data)
            }
            context.startActivity(intent)
        }

        fun startActivityForResults(context: Context,
                                    fragment: Fragment,
                                    data: Movreak) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVREAK_DATA, data)
            }
            fragment.startActivityForResult(intent, 0)
        }
    }

    override val layoutRes: Int
        get() = R.layout.detail_activity

    lateinit var mViewModel: DetailViewModel
    var mData: Movreak? = null


    override fun onExtractIntents() {
        mViewModel = obtainViewModel()
        mData = intent.getParcelableExtra(EXTRA_MOVREAK_DATA)
    }

    override fun onReady() {
        setupObserver()
        setupToolbar()
        setupData()

        // get favorite
        mViewModel.getFavoriteById(mData?.id ?: 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        setResult(FavoriteFragment.CODE_SHOULD_REFRESH_DATA)
        super.onBackPressed()
    }

    fun setupObserver() = mViewModel.apply {
        favoriteStatus.observe(this@DetailActivity, Observer {
            it?.let {
                when (it) {
                    true -> imgFavorite.setImageResource(R.drawable.ic_loved)
                    false -> imgFavorite.setImageResource(R.drawable.ic_love)
                }
            }
        })

        globalToast.observe(this@DetailActivity, Observer {
            toastSpammable(it)
        })
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setupData() {
        Glide.with(this)
                .load(Const.BASE_IMAGE_PATH_W780 + mData?.backdropPath)
                .withLoadingPlaceholder(this)
                .into(imgPhoto)
        Glide.with(this)
                .load(Const.BASE_IMAGE_PATH_W300 + mData?.posterPath)
                .withLoadingPlaceholder(this)
                .into(imgPoster)
        textToolbarTitle.text = mData?.title ?: "-"
        textTitle.text = mData?.title ?: "-"
        textRating.text = mData?.rating.toString()
        ratingBar.rating = ((mData?.rating ?: 0.0) / 2).toFloat()
        textDesc.text = mData?.overview ?: "-"


        imgFavorite.setOnClickListener {
            mViewModel.setFavorite(mData)
        }
    }
}