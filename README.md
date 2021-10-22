# Android-Changhwan
![github_이창환_ver1-24](https://user-images.githubusercontent.com/70698151/135754493-6025ae3d-c4d2-4181-8359-1d921f91d59e.png)

<details markdown="1">
<summary>1주차</summary>

# -실행화면

https://user-images.githubusercontent.com/54737136/136679833-82fab293-e492-499e-a809-3e36ce005694.mp4

# -코드설명

## signin
```
binding.loginButton.setOnClickListener {
            if (binding.inEditId.text.toString() != "" && binding.inEditPw.text.toString() != "") {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "이창환님 환영합니다", Toast.LENGTH_SHORT).show()
            } else {
                binding.inEditId.text.clear()
                binding.inEditPw.text.clear()
                Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()
            }
        }
```
edit text에 내용이 있나 확인후 있다면 토스트메세지를 띄우며 인텐트 하는부분

```
 getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                binding.inEditId.text.clear()
                binding.inEditId.text.append(it.data?.getStringExtra("Id"))
                binding.inEditPw.text.clear()
                binding.inEditPw.text.append(it.data?.getStringExtra("Pw"))
            }
        }
```
signup으로 넘어갔다 돌아왔을때 데이터 받아와서 처리하는 파트


## signup

```
binding.signUpDone.setOnClickListener{
            if(binding.upEditId.text.toString() != "" && binding.upEditPw.text.toString() != "" && binding.upEditName.text.toString() != ""){
                val intent = Intent(this,SignInActivity::class.java).apply {
                    this.putExtra("Id",binding.upEditId.text.toString())
                    this.putExtra("Pw",binding.upEditPw.text.toString())
                }
                setResult(RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this,"입력되지않은 정보가 있습니다",Toast.LENGTH_SHORT).show()
            }
        }
```
finish로 돌아가면서 가져가야하는 데이터들 putExtra로 가져가는부분

## home

```
binding.homeToGit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/2chang5"))
            startActivity(intent)
        }
```
암시적 인텐트로 웹으로 넘어가는부분


# -이번 과제를 통해 배운내용

## level1

### 1. Editable
edittext 를 코드단에서 텍스트를 넣어주기위해 

![image](https://user-images.githubusercontent.com/54737136/136599655-e311e8e0-332c-4ca8-9805-53f3b74765b0.png)

이런식으로 텍스트에 직접 문자열을 넣어줬는데 자료형이 안맞아서 적용할수가 없었다.
edittext의 텍스트는 Editable TYPE이였는데
Editable라는 인터페이스를 구현한 객체이므로 Editable 안에 정의된 clear() append()같은 메서드를 사용해서 조작할수있었다.

![image](https://user-images.githubusercontent.com/54737136/136599778-400c4046-3e45-4d60-bcce-bf2c94410cfa.png)


### 2.finish
기존에 맨날 페이지를 이동할때 startActivity를 통해서만 움직였고 뭔가 이상했다.
스택에 쌓여있는 거쳐왔던 화면으로 돌아갈때는 back 버튼을 물리적으로 눌러서 돌아갔는데 
이렇게 기존 스택에 최상단에있는 화면에서 스택에 쌓여있는 화면으로 돌아가기 위해
백버튼과 같은 효과를 가진 finish() 를 통해서 화면을 종료하고 기존에 화면으로 돌아갈수있다.

![image](https://user-images.githubusercontent.com/54737136/136600033-4af66c31-c17e-4c24-a233-cb83914e76f2.png)

사용예시


### 3.imageView
image view를 사용할때 기존에는
srcCompat 속성에 소스를 설정했는데 이번에 이상하게 이미지가 로드되지 않았다
구글링해본결과

srcCompat은 Android Support Library에 포함된 방식(method of work)이다. (AppCompat에 있음)
안드로이드 서포트 라이브러리를 간단하게 의미하면 '어느 버전에서나 똑같이 구현할 수 있는'을 행하는 라이브러리라고 정의할 수 있다.
srcCompat은 vector Drawables(즉, 그림)를 모든 안드로이드에서 표현하게 해주는 안드 서포트 라이브러리 안에 구현된 기능이다. 그러므로 내가 해당 라이브러리를 쓰고 있는 것이 아니라면 당연히 srcCompat으로 리소스를 지정해봤자 에뮬레이터가 제대로 그려줄 리 없다.
그러므로 srcCompat을 사용하여 이미지를 그리고 싶다면 ImageView태그가 아닌 android.support.v7.widget.AppCompatImageView를 사용해야 한다.
또한 네임스페이스도 지정해줘야 한다 (xmlns:app="http://schemas.android.com/apk/res-auto")

minSDKversion이 롤리팝(5.0, level 21)이상이라면 src방식을 사용할 수 있는데 이때부터 안드로이드에서 머테리얼 디자인이 생겼기 때문이다.
src의 경우 xml에서 ImageView태그일 경우에 사용할 수 있으며 이때부터 src 설정을 사용하여 이미지를 넣을 수 있다.
srcCompat과 달리 롤리팝 이상부터 기본으로 지원하며 롤리팝 이전 버전을 지원해야 할 경우에는 반드시 srcCompat을 사용해야 한다.

과거에는 모든 버전을 커버할 수 있다는 점 때문에 srcCompat이 나름 가치가 있었으나 현재는 대부분의 사람들이 최소 롤리팝 이상의 버전을 쓰기 때문에 장점이 상당히 희석되었다. 

출처: https://ammff.tistory.com/100 [아메리카노가 그렇게 맛있답니다 여러분]

이렇다고한다.

그래서 src 속성으로 바꾸니 해결되었다.

## level2
### 1.registerForActivityResult

(추후에 한번 살펴볼것: parcelable로 객체 전달해보자)

원래는 기존에 startActivityForResult() 와 onActivityResult()을 사용했었는데 deprecated 되고 그 대용으로
registerForActivityResult가 들어왔다. 

용도를 살펴보자면

startActivity : 새 액티비티를 열어줌 (단방향)

registerForActivityResult : 새 액티비티를 열어줌 + 결과값 전달 (쌍방향)

간단히 말해서 액티비티를 열되 갈때는 하던대로 putExtra() 를 이용해서 데이터 전달하고
열린 액티비티가 finish로 종료되었을때 필요한 정보를 가지고 기존 액티비티로 돌아올수있게 하는것이다.
그리고 종료시점에 코드를 실행해주어 가지고온 데이터를 처리할수있다.

기존 startActivityForResult() 와 비교하여 장점은 이러하다.

-디커플링 및 관심사 분리 : 기존 액티비티 또는 프레그먼트의 onActivityResult에서 if와 else if로 도배되던 비즈니스 로직들이 콜백메서드 또는 분리된 클래스 단위로 쪼개어져서 관리될 수 있다. 이는 코드의 가독성을 높이고, 유닛테스트를 수월하게 하며, 유지보수측면에서도 많은 도움이 된다.

-Type-Safety : ActivityResultContract는 입력 데이터와 출력 데이터의 타입을 강제하기 때문에 잘못된 타입으로 캐스팅하는 사소한 실수를 미연에 방지시켜준다.

-NPE 방지 : Intent로 부터 데이터를 얻으려고 할 때 NullPointerException이 발생하는 경험을 누구나 한번쯤은 해보았을 것이다. 새로운 API는 NPE가 발생할 확률을 줄여줄 것이다

출처 : https://charlezz.medium.com/%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EA%B2%B0%EA%B3%BC-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0-good-bye-startactivityforresult-onactivityresult-82bafc50edac

위의 링크에 내부적으로 어떻게 돌아가는지에대해 자세히 나와있다.
contract를 직접 정의하고 등록하여 사용할수도있지만
이미 정의된 contract를 사용할수도있다.

![image](https://user-images.githubusercontent.com/54737136/136600592-ba246845-b2be-4d91-b29f-7587e702b22c.png)

이렇게 정의된 contract를 적절한 시기에 사용하면된다.

예를들어 이미지 등의 컨텐츠의 uri를 받아오고싶다면 getContent를 사용하게되는식이다.



이제 간단하게 사용법을 보자면

a엑티비티에서 b 엑티비티로 넘어갔다가 정보를 들고 a로 다시 넘어오는 상황이다
우선 a액티비티에서는 registerForActivityResult함수를 이용해서 callback을 등록해준다.
result를 받기위해 StartActivityForResult를 이용한다.

![image](https://user-images.githubusercontent.com/54737136/136600715-aaca32b5-03e8-4159-9a6e-5cc0682517e8.png)

람다식 안에서
그래서 result로 넘어오는 결과를 resultCode를 확인하고 data를 받아서 데이터 처리를 해주면된다.
data에는 intent에 putExtra로 넣어놓은것들을 뽑아서쓸수있다.


그리고 이제 lacuch를 시켜줘야하는데 intent에 넘어가려는것들 요소 넣어주고
인자에 Intent를 넘겨주며 launch를 실행시킨다.

![image](https://user-images.githubusercontent.com/54737136/136600778-b0460655-b2c2-4329-910e-327bfc550dfe.png)

다음 엑티비티b에서 해야할일을보자

엑티비티 b에서는 intent에 원하는 정보 putExtra로 넣고 setResult함수에 인자로 resultcode와 intent를 넣어주고
finish()로 a로 넘어가면된다.

![image](https://user-images.githubusercontent.com/54737136/136600833-ea0119ff-6435-42a4-81b9-12047e6cfc59.png)


## 2.명시적,암시적 인텐트
인텐트는 한마디로 화면이 옮겨지거나 전화를 걸거나 웹페이지들을 열거나 할때 정보를 4대 컴포넌트끼리 유기적으로 정보전달을하며 작동할수있게 해주는 요소이다.

또한 인텐트는 자신이 만든 앱안에서 활동하는 것 뿐만 아니라 내가 만들지 않은 타 애플리케이션의 기능을 수행할 수 있다.
즉 안드로이드 시스템은 내가 만든 인텐트의 정보를 처리하면서 내가 만든 액티비키나 애플리케이션의 구성요소가 해야할 일을 지정하는 것 이외에도 타 애플리케이션의 기능을 수행하는 등 훨씬 유연한 기능의 애플리케이션을 만들 수 있게 한다.

이제 명시적 인텐트와 암시적 인텐트의 차이점을 봐보자

명시적이벤트: 인텐트에 클래스 객체나 컴포넌트 이름을 지정하여 호출할대상을 확실히 알수있는경우에 사용,

주로 어플리케이션 내부에서 사용한다.

-> 특정 컴포넌트나 액티비티가 명확하게 실행되어야할경우 사용한다. 즉 화면이동 등 앱내에서 주로 사용된다.

암시적인텐트:
인텐트의 액션과 데이터가 정해졌지만 호출할 대상이 달라질수있는경우 암시적 인텐트를 사용
예를들어 웹을 여는경우에 직접 구현하지 않고 안드로이드 시스템내에 있는 웹브라우져를 끌어다 쓰는데 
그때 브라우져는 여러개가 깔려있을수 있기에 정확히 어느걸 호출할것인지 모르는 상황이다. 이런경우 암시적 인텐트를 통해 정보처리를 할수있는 적절한 컴포넌트를 찾아와 사용자에게 고르게하고 그에의해 처리한 결과를 보여주는것이다.

한마디로 정리해서 일과 데이터는 정해졌는데 외부에서 처리하려할때 그 일을 누가할지는 정해져있지않아 그냥 떠넘겨 버리고 그일을 할 프로그램은 안드로이드 내부적으로 정해지거나 여러개일경우 사용자가 정하도록 하는것이다.

## 3.ConstraintDimensionRatio
비율을 정해서 사용하고싶을때 weidth나 height 둘중하나만 정하고 하나는 0dp로 설정해주고
가로 세로의 비율을 ConstraintDimensionRatio 속성을 통해서 정해준다

비율 표현방법은 이러하다

- app:layout_constraintDimensionRatio="1:1" (width:height로 표현하는 방법)

- app:layout_constraintDimensionRatio="1.0" (width와 height의 비율을 float값으로 표현하는 방법)

</details>

<details markdown="1">
<summary>2주차</summary>

# - 실행화면

<video src="C:\Users\chang\Documents\Bandicam\bandicam 2021-10-22 17-29-56-316.mp4"></video>





# - 코드설명

일단 follower는 리스트형태 repository는 그리드 형태로해서 level2,3 적용은 다 follower에다가만 했다.



level1은 당연히 수행했고



### level2



level 2-1은

들어갈때 상세화면을 구성하는것은 DetailFragment를 만들어 거기에 데이터를 arguments로 전달해서 구성했고

밑에 설명은 이름이 뭔가에 따라서 그냥 databinding으로 지가알아서 바뀌게 만들었다.

보기좋으라고 필요한부분만 코드를 잘라서 넣어놨는데 잘한건지는 모르겠다 피드백 부탁드립니다.



**2-1**

DetailActivity.kt

```

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var name: String
    private var src by Delegates.notNull<Int>()
    lateinit var detailIntroduce : MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detail = this
        binding.lifecycleOwner = this

        name = intent.getStringExtra("name")!!
        src = intent.getIntExtra("src",R.drawable.pig)

        siteFragment()
        decideDetailIntroduction()

    }

    fun siteFragment(){
        val detailFragment = DetailFragment()
        var bundle = Bundle()
        bundle.putString("name",name)
        bundle.putInt("src",src)
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.detailFragmentFrame,detailFragment).commit()
    }

    fun decideDetailIntroduction(){
        if (name == "문다빈"){
            detailIntroduce=MutableLiveData<String>().apply { value = "고향은 경상남도 합천이고,현재 24살이며 안드로이드 파트장을 맡고있음.. 안드 좋아. 안드 좋아. 안드 좋아.안드 좋아. 안드 좋아. 안드 좋아. 안드 좋... "}
        }else if (name == "장혜령"){
            detailIntroduce=MutableLiveData<String>().apply { value = "누군지 몰라요 최송합니다"}
        }
   // 중략

    }

}
```



DetailFragment.kt

```
package changhwan.experiment.sopthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.databinding.FragmentDetailBinding
import kotlin.properties.Delegates


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var name : String
    private var src by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = arguments?.getString("name")!!
        src = arguments?.getInt("src",R.drawable.pig)!!
        binding.detailImage.setImageResource(src!!)
        binding.detailName.text = name
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```





**2-2**

과제중 알게된것에 자세히 설명해놨다

CustomDividerDecoration.kt

```
package changhwan.experiment.sopthomework

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(private val height: Float,private val padding: Float, @ColorInt private val color: Int,private val margin : Int):RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init{
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = ( child.bottom.toFloat() + margin)
            val bottom = child.bottom.toFloat() + height + margin

            c.drawRect(left, top, right, bottom, paint)

        }
    }
}
```



CustomMarginDecoration.kt

```
package changhwan.experiment.sopthomework

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomMarginDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
        outRect.bottom = padding
        outRect.left = padding
        outRect.right = padding
    }
}
```



FollowerFragment.kt

```
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        siteFollowerRecycler()

        binding.followerRecycle.addItemDecoration(CustomMarginDecoration(50))
        binding.followerRecycle.addItemDecoration(CustomDividerDecoration(10f,10f, resources.getColor(R.color.main),40))
```



**2-3**

이것도 과제중 알게된것에 자세히 설명해놨다



itemActionListener.kt

```
package changhwan.experiment.sopthomework

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
```



ItemDragListener.kt

```
package changhwan.experiment.sopthomework

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
```



FollowerAdapter.kt

```
class FollowerAdapter(private val listener: ItemDragListener) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {
    //...
    
     override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = followerData.removeAt(from)
        followerData.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerData.removeAt(position)
        notifyItemRemoved(position)
    }
    
    
    inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) : RecyclerView.ViewHolder(binding.root) {
       
        init {
           
            binding.root.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

    }
}
```



ItemTouchHelperCallBack.kt



```
package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwiped(viewHolder!!.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = true
}
```



FollowerFragment.kt

```
class FollowerFragment : Fragment(), ItemDragListener {
	
    private lateinit var itemTouchHelper : ItemTouchHelper
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
    }
    
     override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        //이부분 참고한 블로그와 다르게 아무것도 없어야지만 돌아간다 이해안됨 이부분은 추가적인 공부해야겠다
    }
}
```







### level3

**3-1**

은 정보만 과제중 알게된것에 작성하고 적용하지는 않았다.



**3-2**

DiffUtil은 oldList와 newList를 비교하여 차이를 계산하고, newList로 갱신해주는 유틸리티 클래스이다.

즉, 이 클래스를 사용하면 아이템 변경의 구체적인 상황에 따라 Adapter의 적절한 메소드를 호출하지 않아도 된다.



ContactDiffUtill.kt

```
package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.DiffUtil

class ContactDiffUtil(private val oldList: List<FollowerData>, private val currentList: List<FollowerData>):
    DiffUtil.Callback(){
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =currentList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].followerName==currentList[newItemPosition].followerName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==currentList[newItemPosition]
    }

}
```

총 4개 메소드를 오버라이드 해줘야 한다. 메소드는 이름명과 리턴값을 보면 어떤 역할을 하는지 쉽게 예측할 수 있다.



FollowerAdapter.kt

```
//diffUtill 부분 이상하면 나중에 바꿔야함
fun setContact(contacts: List<FollowerData>){
    val diffResult= DiffUtil.calculateDiff(ContactDiffUtil(this.followerData, followerData), false)
    diffResult.dispatchUpdatesTo(this)
    this.followerData=followerData
}
//여기까지 diffUtill
```

코드의 뜻은,

1. calculateDiff()로 oldList와 newList의 차이를 계산한다.
2. 차이 값을 업데이트하고, (notify~ 기능와 같다고 보면 된다).
3. list가 갱신되었으므로 기존 this.contacts를 newList인 contacts로 업데이트한다.





FollowerFragment.kt

```
//diffUtill부분 원래는 followerAdapter.notifyDataSetChanged()였음
followerAdapter.setContact(followerAdapter.followerData)
//여기까지
```

# -이번 과제를 통해 배운내용



## level2

### 2-1



#### 1.리사이클러뷰 항목마다 이벤트 리스너 달아주기



각 항목마다 클릭 이벤트 리스너 달아서 이벤트를 처리해보자

ViewHolder 혹은 onBindViewHolder() 함수 두곳에서 이벤트 처리 하는 방법이 있는데



우선 viewholder에서 처리해주는것부터 부터 봐볼것이다.



**1-1ViewHolder에서 처리**

우리가 viewbinding 을 이용해서 viewHolder를 만들었기에 ViewHolder의 생성자로 binding객체를 꽂아줬다.

그래서 이 binding객체의 root가 리사이클러뷰가 표현하는 항목하나 즉 item의 레이아웃에 접근할수있다

그래서 init함수를 만들어 root에 onClicklistener를 추가한다.

![img](https://blog.kakaocdn.net/dn/91zgE/btripFeEZ0k/Da5dKh4z0OaHsgxkGNwX0K/img.png)





**1-2 onBindViewHolder() 에서 처리**



이 함수 안에서도 item에 대한 클릭 리스너를 정의할수있다. 하지만 난 앞전의 방법을 사용했다

![img](https://blog.kakaocdn.net/dn/LmcbP/btriwwzZJHf/VYiQ0o2zrkKTtgiKwEKalk/img.png)

결과적으로 Holder 클래스 내부에서 사용하는것과 같다.





### 2.리사이클러 뷰 어댑터에서 startActivity해보기



리사이클러 뷰에서 아이템을 클릭해서 이벤트를 발생까지는 시켰는데 원하는 이벤트가 새로운 액티비티 실행일 경우

어댑터에서 이벤트를 실행시켜주고있기에 기존에 액티비티에서 Intent에 넣어줬던 인자들을 그대로 넣어주면 안된다.



일단 clickListener안에 intent추가하고 첫번째 인자로 binding.root에 context가 있기에 그걸 사용한다.

그래서 컨텍스트를 맞춰서 넣어주고 두번째 인자로 이동하려는 액티비티 넣고



startActivity함수에 첫번째 인자로 binding.root의 context, 두번째 인자로 intent, 세번째로 별다른 옵션이없다면 null을 입력하면된다.





![img](https://blog.kakaocdn.net/dn/kFvli/btrioFZW6lG/yJeInBOnV5kWekkRW6Mhc0/img.png)





### 3. activity에서 activity로 데이터 옮기기



**데이터 보내기**

첫번째 방법

```
// 제일 단순하고 쉬운 방법
val intent = Intent(this,옮겨갈 액티비티::class.java)
intent.putExtra("num1",1) //데이터 넣기
intent.putExtra("num2",2) //데이터 넣기
startActivity(intent)
```

두번째 방법

```
val intent = Intent(this@Intent1,Intent2::class.java).apply {
	this.putExtra("num1",1) // 데이터 넣기
   	this.putExtra("num2",2) // 데이터 넣기
}
startActivity(intent)
//코틀린의 유용한 기능!🤩 apply
//한눈에 모아서 볼 수 있어서 유용한 듯
```



**데이터 받기**

```
val number1 = intent.getIntExtra("num1", 0)
val number2 = intent.getIntExtra("num2", 0)
```





### 4. Activity에서 Fragment로 데이터 주고 받기

**데이터 보내기**

```
var fragment2 = Fragment2()
var bundle = Bundle()
bundle.putInt("num1",1)
bundle.putInt("num2",2)
fragment2.arguments = bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

activity?.supportFragmentManager!!.beginTransaction()
                        .replace(R.id.view_main, fragment2)
                        .commit()
```



**데이터 받기**

```
val num1 = arguments?.getInt("num1")
val num2 = arguments?.getInt("num2")
```





### **2-2**



itemDecoration 활용해서 구분선과 간격주기



xml파일에서 margin이나 구분선을 어느정도 만들수있지만 이거는 정확히말하자면 상하단 끝쪽에 margin은 한번만 들어가고 나머지 중간부분은 두번씩 들어가는문제

구분선은 xml내에서 view를 추가해서 넣으면 레이아웃에 불필요한 뷰를 추가함으로써 레이아웃 계층이 증가하고 그에따라 성능에 안좋은 영향을 미치며

좌우로 스와이프 하는 애니메이션이있다면 구분선이 같이 움직인다.



그래서 itemDecoration을 사용하는데 itemDecoration 클래스는 Recyclerview 내부에 있는 추상 클래스이다.

이름처럼 RectclerView의 아이템들을 꾸미는 역할을 한다.

사실 커스텀 하는대로 많은 기능들을 구현할 수 있으므로 하고싶은게 있으면 구글링해서 사용해야겠다 근데 왜 죄다 예제코드가 자바냐고!!!!!!!!!!!!!

대표적으로 구분선이나 여백을 넣는데 많이들 사용한다.





내부 함수가 3가지가 있는데

1.onDraw

아이템이 그려지기 전에 호출됨으로 아이템(viewholder)보다 아래에 위치하게된다 아이템과 onDraw가 그리는 것이 겹친다면 아이템이 덮어씌워서 onDraw가 그리는 것이 안보인다.



2.onDrawOver

아이템이 그려지고 난다음에 호출되는 함수로 이거는 겹친다면 아이템을 가릴수있다.



3.getItemOffsets

각 항목을 배치할때 호출한다 -> margin을 줄때 사용

outRect에 원하는 크기에 간격을 (왼쪽, 위쪽, 오른쪽, 아래쪽) 의 4개 필드에 설정해준다.





그래서 일단 만들어보자.



구글링하면 예제 겁나게 많다 복잡한거는 더더욱많다. 그리고 뭔소리인지 이해하기 좀 난해하다

그냥 쉬운 것들로 적용해보며 필요할때 마다 만들어서 사용해 나가고 난이도를 조금씩 높여야겠다.



**가장쉬운 margin만들기**

```
package changhwan.experiment.sopthomework

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomMarginDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
        outRect.bottom = padding
        outRect.left = padding
        outRect.right = padding
    }
}
```

따로 class파일을 하나파서 itemDecoration을 상속받은후

생성자로 띄울 값을 받아서

getItemOffsets를 오버라이딩 할때 사방 top,bottom,left,right에 생성자로 받은 값을 넣어서 margin을 확보했다.



적용은 적용시킬 리사이클러뷰에다

```
 binding.followerRecycle.addItemDecoration(CustomMarginDecoration(50))
```

이런식으로 addItemDecoration으로 넣어주면된다.(생명주기상 화면이 그려지고 난후에 실행시켜야하는것 같다)





**가장쉬운 구분선 만들기**

```
package changhwan.experiment.sopthomework

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(private val height: Float,private val padding: Float, @ColorInt private val color: Int,private val margin : Int):RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init{
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = ( child.bottom.toFloat() + margin)
            val bottom = child.bottom.toFloat() + height + margin

            c.drawRect(left, top, right, bottom, paint)

        }
    }
}
```



구분선이 좀빡세다



for문에 들어가는것들이 뭔소리인가 싶은데 좀 공통적으로 예제마다 거의 겹친다.



몇가지 예제들의 부분부분을 따서 섞어서 사용했다



생성자에 들어가는 height에 따라 구분선의 굵기가 달라지고

padding에 따라 좌우에 여백이생기며

color은 색깔 설정이고

margin은 얼마나 띄울지 거리설정이다.

근데 이런것도 내가 뭐넣을지 알아서 결정이다 어짜피.





### **2-3**

recyclerview의 drag&drop swipe to Dismiss 구현



[ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper)는 RecyclerView.ItemDecoration의 서브 클래스이다. RecyclerView 및 Callback 클래스와 함께 작동하며, 사용자가 이러한 액션을 수행할 때 이벤트를 수신한다. 우리는 지원하는 기능에 따라 메서드를 재정의해서 사용하면 된다.



[ItemTouchHelper.Callback](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.Callback)은 추상 클래스로 추상 메서드인 getMovementFlags(), onMove(), onSwiped()를 필수로 재정의해야 한다. 아니면 Wrapper 클래스인 [ItemTouchHelper.SimpleCallback](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.SimpleCallback)을 이용해도 된다.



이제 순서대로 구현하는것을 쫒아가보자



**1.ItemDragListener.kt 만들기**

```
interface ItemDragListener {
    fun onStartDrag(viewHolder :RecyclerView.ViewHolder)
}
```

사용자가 Drag 액션을 시작할 때 itemTouchHelper에 이벤트를 전달한다.





**2.ItemActionListener.kt 만들기**

```
interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
```

아이템이 Drag & Drop 됐거나 Swiped 됐을 때 어댑터에 이벤트를 전달한다.





**3.adapter에서  ItemActionListener 인터페이스를 구현**

```
class FollowerAdapter(private val listener: ItemDragListener) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {
    //...
    
     override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = followerData.removeAt(from)
        followerData.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerData.removeAt(position)
        notifyItemRemoved(position)
    }
}
```



어댑터에서는 ItemActionListener 인터페이스를 구현한다. onItemMoved(), onItemSwiped()을 재정의하여 아이템 이동과 제거 코드를 작성한다. 이때 어댑터가 아이템 변경 사항을 인식할 수 있도록 notifyItemMoved(), notifyItemRemoved()를 호출해야 한다.



**4.viewAdapter에서 OnTouchListener달아주기**

```
inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) : RecyclerView.ViewHolder(binding.root) {
       
        init {
           
            binding.root.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

    }
```



어댑터 생성자의 파라미터로 받은 ItemDragListener는 뷰홀더에서 사용된다. 여기서는 드래그 핸들을 통한 아이템 이동을 구현하고자 하기 때문에, 드래그 핸들 뷰에 터치 리스너를 달아준다. 그리고 사용자가 [ACTION_DOWN](https://developer.android.com/reference/android/view/MotionEvent.html#ACTION_DOWN) 액션을 취했을 때 listener.onStartDrag()를 호출한다.





**5.ItemTouchHelperCallback.kt 작성**

```
package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwiped(viewHolder!!.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = true
}
```



[ItemTouchHelper.Callback](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.Callback)을 상속받는 ItemTouchHelperCallback 클래스를 구현한다. 생성자의 파라미터로 ItemActionListener를 받는다.



5-1 우선 getMovementFlags()를 재정의해 Drag 및 Swipe 이벤트의 방향을 지정한다.



5-2 아이템이 Drag 되면 [ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper)는 onMove()를 호출한다. 이때 ItemActionListener로 어댑터에

fromPosition과 toPosition을 파라미터와 함께 콜백을 전달한다.



5-3 아이템이 Swipe 되면 [ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper)는 범위를 벗어날 때까지 애니메이션을 적용한 후 onSwiped()를 호출한다.

이때 ItemActionListener로 어댑터에 제거할 아이템의 position을 파라미터와 함께 콜백을 전달한다.



5-4 isLongPressDragEnabled()은 아이템을 길게 누르면 Drag & Drop 작업을 시작해야 하는지를 반환한다. 디폴트는

true이다





**6.사용처(activity 혹은 fragment) 에서 액티비티에서는 ItemDragListener 인터페이스를 구현**

```
class FollowerFragment : Fragment(), ItemDragListener {
	
    private lateinit var itemTouchHelper : ItemTouchHelper
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
    }
    
     override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        //이부분 참고한 블로그와 다르게 아무것도 없어야지만 돌아간다 이해안됨 이부분은 추가적인 공부해야겠다
    }
}
```



이렇게하면 원했던 기능들을 구현할수있다.







## **Level3**



### **3-1**



보일러 플레이트 코드 어떻게 잡을것인가?

딱히 당장 많이 개선할부분이 없는데 복잡해서 방법만 알아두고 가야겠다.



어노테이션 프로세서 같은걸 사용해서 자동화 작업을 하는것이 좋다지만 너무 복잡하다 당장 이거하다 머리터진다.



그래서 복잡하지 않은 방법을 봤다

base코드 즉 BaseActivity, BaseFragment같은 코드들을 만들어놓고 상속해서 사용하는것이다.



장단점을 살펴보면



**장점**



-데이터 바인딩뿐만 아니라 Activity들이 공통적으로 수행해야 하는 코드가 있다면 BaseActivity를 이용할 수 있다.따라서



-Bolierplate 코드가 줄어든다.



**단점**



-AppCompatActivity가 아니라 특정한 Activity를 상속해야만 하는 경우가 있다.
=> 어쩔 수 없이 BaseActivity를 쓰지 못한다.



-BaseActivity가 변경되면 이를 상속한 모든 Activity들이 변경되는 것이므로 부담이 크다. (Side Effect)



-공동작업을 할 때 다른 사람이 코드를 이해하기 어렵다.





이제 basecode예시 잘 들어놓은 블로그 링크를 걸어놓겠다 나중에 시도해봐야겠다 ㅎㅎ



[액티비티](https://seunghyun.in/android/1/)



[프래그먼트](https://youngest-programming.tistory.com/285)





### **3-2**

notifyDataSetChanged의 문제점!!



**리스트 업데이트 하는데 5가지 방법이있다**

리스트를 업데이트 하는방법중 가장 큰범위인 리스트의 크기와 아이템이 둘다 변경되는 경우에 사용하면 되는 것인

notifyDataSetChanged를 무지성으로 쓰면 모든경우에 다 적용이야 되겠지만 비효율적으로 움직일것이다.





그러므로 나머지 4가지 방법을 적재적소에 이용해서 자원을 아끼자



관련 방법들을 잘정리해 놓은 블로그가있어 링크로 남겨놓겠다



[**관련 함수설명 링크**](https://todaycode.tistory.com/55)





자근데 이방법 말고 더 참신한거 써보자



바로 DiffUtil 이다.



[https://velog.io/@deepblue/RecyclerView%EC%9D%98-notifyDataSetChanged](https://velog.io/@deepblue/RecyclerView의-notifyDataSetChanged)



이블로그에 있는거 그대로 구현해봤는데 뭔가 틀린부분이 분명있을거같다 급하게해서 돌아는가는데

어쨋든 추후에 다른자료들과 비교해보면서 체크해봐야겠다



</details>