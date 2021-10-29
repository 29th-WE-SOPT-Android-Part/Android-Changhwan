package changhwan.experiment.sopthomework

import android.content.Intent
import android.database.DatabaseUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import changhwan.experiment.sopthomework.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {

    private var _binding : FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var getContent: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_camera, container, false)




        binding.lifecycleOwner = this

        initPicUri()
        initIntent()

        return binding.root
    }

    private fun initPicUri(){


        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val cameraData = CameraData(picUri = MutableLiveData<Uri>().apply { value = it.data?.data })
            binding.camera = cameraData
        }
    }

    private fun initIntent(){
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = MediaStore.Images.Media.CONTENT_TYPE
            type = "image/*"
        }

        binding.cameraButton.setOnClickListener{
            getContent.launch(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}