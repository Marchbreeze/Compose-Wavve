## 1. 리스트 & 그리드 구조

### (1) data class & item

- data class

    ```kotlin
    data class Music(
        val ranking : Int,
        val title : String,
        val singer : String
    )
    ```


- data

    ```kotlin
    val musics = listOf<Music>(
        Music(
            ranking = 1,
            title = "HAPPY",
            singer = "DAY6 (데이식스)"
        ),
        Music(
            ranking = 2,
            title = "UP (KARINA Solo)",
            singer = "aespa"
        ),
    ```


- ui

    ```kotlin
    @Composable
    fun Music(music: Music) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = music.ranking.toString())
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = music.title)
                Text(text = music.singer)
            }
        }
    }
    ```

  ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/ab661aaf-9094-42c3-a061-c6604b84f8ef/image.png)


### (2) LazyLists

- LazyLists는 화면의 ViewPort에 보이는 항목 즉, ****화면에 보여지는 항목들만 생성할 수 있도록 함
    - 일반 List로는 성능이 떨어지고, 스크롤 지원 설정을 해야 함

        ```kotlin
        val scrollState = rememberScrollState()
        
        Column(modifier = Modifier.verticalScroll(state = scrollState)) {
            musics.forEach { music ->
                Music(music = music)
            }
        }
        ```

    - LazyColumn, LazyRow, LazyGrids가 포함되며, 각각은 아래와 같은 형태로 아이템들을 배치

      ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/51120965-f402-4f92-9784-bfeb18c4f449/image.png)


- 상세보기

    ```kotlin
    @Composable
    fun LazyColumn(
        modifier: Modifier = Modifier,
        state: LazyListState = rememberLazyListState(),
        contentPadding: PaddingValues = PaddingValues(0.dp),
        reverseLayout: Boolean = false,
        verticalArrangement: Arrangement.Vertical =
            if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
        userScrollEnabled: Boolean = true,
        content: LazyListScope.() -> Unit
    ) {
        LazyList(
            modifier = modifier,
            state = state,
            contentPadding = contentPadding,
            flingBehavior = flingBehavior,
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            isVertical = true,
            reverseLayout = reverseLayout,
            userScrollEnabled = userScrollEnabled,
            content = content
        )
    }
    ```

    - 간격 설정 : horizontalArrangement = Arrangement.spacedBy(12.dp)
    - 리스트 자체의 패딩 : contentPadding = PaddingValues(horizontal = 16.dp)
    - LazyListScope
        - 다른 Compose 레이아웃과는 다르게, 직접적으로 컴포저블을 배출할 수 있게 해주는 @Composable 블록 매개변수가 아닌 LazyListScope.() 블록을 제공

- 구현

    ```kotlin
    LazyRow(
        modifier = Modifier.padding(bottom = 10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
    	// 여기에 들어갈 수 있는 코드들입니다.
    }
    ```

    1. 단일 항목 추가

        ```kotlin
        // EmptyView 설정
        if (images.isEmpty()) {
        	item {
              EnrollAddPhotoButton(
                  onClick = onPhotoButtonClick
              )
          }
        }
        ```

    2. 여러 항목 추가

        ```kotlin
        // List를 인자로
        items(images) { image ->
            EnrollPhotoPreviewCard(
                id = 1,
                isDeletable = isDeletable,
                image = image,
                onDeleteButtonClick = {}
            )
        }
        
        // 리스트의 크기를 인자로
        items(images.size) { index ->
            EnrollPhotoPreviewCard(
                id = index,
                isDeletable = isDeletable,
                image = images[index],
                onDeleteButtonClick = { onDeleteButtonClick(index) }
            )
        }
        
        // 객체와 인덱스 모두 반환
        itemsIndexed(timeline.tags) { index, tag ->
            DateRoadImageTag(
                textContent = stringResource(id = tag.titleRes),
                imageContent = tag.imageRes,
                tagContentType = timelineType.tagType,
                modifier = Modifier.padding(start = if (index > 0) 6.dp else 0.dp)
            )
        }
        ```


### (3) Key & ContentType

- LazyLists 내에서 아이템을 만들 때 key값과 contentType을 추가적으로 받을 수 있음
    1. 항목 재활용 최적화 : LazyList는 Key를 기반으로 항목을 재활용하므로, 고유한 키가 없으면 항목이 불필요하게 다시 렌더링될 수 있음
    2. 올바른 UI 업데이트 보장 : 리스트의 항목이 추가, 삭제, 이동될 때도 올바르게 항목을 매칭

        ```kotlin
        LazyColumn {
            items(
                items = listOf("Header", "Item1", "Item2", "Footer"),
                key = { it }, // 항목의 고유값을 Key로 사용
                contentType = { item -> // 콘텐츠 유형을 지정
                    when (item) {
                        "Header" -> "HeaderType"
                        "Footer" -> "FooterType"
                        else -> "ItemType"
                    }
                }
            ) { item ->
                when (item) {
                    "Header" -> Text(text = "Header Section")
                    "Footer" -> Text(text = "Footer Section")
                    else -> Text(text = "Item: $item")
                }
            }
        }
        ```


1. Key
    - XML의 DiffUtil
    - LazyLists 내에서 key는 고유한 값이며 이 값을 바탕으로 recomposition이 이루어지지 말지를 결정
    - 지정하지 않으면 item의 index를 통해 구성 → 아이템 추가/삭제 시 recomposition 발생

1. contentType
    - XML의 멀티뷰타입
    - 다른 형태를 갖는 item을 리스트에 표시하는 경우 각 아이템의 type을 지정하면 성능을 극대화
    - 다른 유형의 콘텐츠는 서로 재활용되지 않도록 분리 & UI 깜빡임 방지

## 2. Scaffold

- Scaffold (골격)
    - 한 화면의 레이아웃 구조를 쉽게 짤 수 있도록 구조가 이미 잡혀진 @Composable
    - Material Design 가이드에 따라 앱의 구조를 빠르게 구성할 수 있는 간단한 API를 제공

1. TopBar

    ```kotlin
    topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("Top app bar")
            }
        )
    }
    ```

2. BottomBar

    ```kotlin
    bottomBar = {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Bottom app bar",
            )
        }
    },
    ```

3. SnackBar

    ```kotlin
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Person, contentDescription = "") },
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Snackbar")
                    }
                }
            )
        }
    ) {
        Text("스낵바")
    }
    ```

4. FAB

    ```kotlin
    floatingActionButton = {
      ExtendedFloatingActionButton(
          text = { Text("Show snackbar") },
          icon = { Icon(Icons.Filled.Person, contentDescription = "") },
          onClick = {
              scope.launch {
                  snackbarHostState.showSnackbar("Snackbar")
              }
          }
      )
    }
    ```

- ex.

    ```kotlin
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldExample() {
        var presses by remember { mutableIntStateOf(0) }
    
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Top app bar")
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { presses++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text =
                    """
                        This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.
    
                        It also contains some basic inner content, such as this text.
    
                        You have pressed the floating action button $presses times.
                    """.trimIndent(),
                )
            }
        }
    }
    ```

    - TopAppBar, BottomAppBar, FAB 등 다른 컴포넌트가 위치한 부분을 제외한 모든 영역이 content
    - InnerPadding
        - Content 매개변수는 다른 매개변수와는 조금 다르게 PaddingValues를 인자로 받음
        - Scaffold에 전달하는 TopAppBar, BottomAppBar의 높이를 나타내며 이를 통해 content에 padding 값이 적용됨 (content가 탑바와 바텀바를 제외한 영역만큼의 크기 제공)


## 3. Single Activity Architechture (SAA)

- Single Activity Architecture(SAA)
    - 앱에서 하나의 Activity만 사용하고, 나머지 모든 화면은 Composable 또는 Fragment로 구성하는 앱 아키텍처
    1. Activity를 전환하는데 드는 리소스가 Fragment를 전환하는데 소모하는 리소스보다 훨씬 큼
    2. Compose의 단일 상태 흐름과 선언적 접근 방식에 맞게 앱을 설계할 수 있음
    3. 상태 관리가 Activity에 집중되므로, 전체 앱 상태를 한 곳에서 관리할 수 있음
    4. Activity가 하나이므로 앱의 생명 주기를 관리하기 쉬움
    5. 전체 앱 테마와 스타일을 Activity의 단일 테마 정의를 통해 쉽게 관리할 수 있음

### (1) Navigation

1. NavHost
    - 현재 탐색 대상이 포함된 UI 요소
    - 사용자가 앱을 탐색할 때 앱은 기본적으로 탐색 호스트 안팎으로 대상을 전환
    - 모든 경로는 NavHost에 선언되어있어야 함
2. NavGraph
    - 앱 내의 모든 탐색 대상과 연결 방법을 정의하는 데이터 구조
    - 목적지를 배열의 형태로 관리해서 앱 내의 모든 경로가 연결되는 방식을 정의하는 데이터 구조
3. NavController
    - 대상 간 탐색을 관리하는 중앙 코디네이터
    - 컨트롤러는 대상 간 탐색, 딥 링크 처리, 백 스택 관리 등의 작업을 위한 메서드를 제공
4. NavDestination
    - 사용자가 이 노드로 이동하면 호스트가 콘텐츠를 표시
    - 탐색 그래프의 노드 (목적지)
5. Route
    - 대상과 필요한 데이터를 고유하게 식별

- 동민이의 구성요약

  **`route`** : 장소를 식별하는 단위에요. 장소의 이름을 나타내요.

  **`destination`** : 도착하고 싶은 장소에요

  **`controller`** : 화면을 이동시켜주고, 데이터를 가져다주는 도구에요

  **`graph`** : 장소들의 연결 방법을 정의하는 구조에요. 모든 경로를 설정해둬요

  **`host`** : 제일 큰 구조로 그래프와 그 외적인 부분들을 가지고 있어요


- 동민이의 디펜던시

    ```kotlin
    // libs.version.toml
    [versions]
    kotlin = "2.0.0"
    androidxComposeNavigation = "2.8.2"
    kotlinxSerializationJson = "1.7.3" // kotlin 2.0 이상 기준
    
    [libraries]
    androidx-compose-navigation = { group = "androidx.navigation", name = "navigation-compose", version.ref = "androidxComposeNavigation" }
    kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson" }
    
    [plugins]
    kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
    ```


- 구현
    1. NavController 선언

        ```kotlin
        val navController = rememberNavController()
        ```


1. NavHost 설정

    ```kotlin
    @Composable
    public fun NavHost(
        navController: NavHostController,
        startDestination: String,
        modifier: Modifier = Modifier,
        contentAlignment: Alignment = Alignment.TopStart,
        route: String? = null,
        enterTransition:
            (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
            {
                fadeIn(animationSpec = tween(700))
            },
        exitTransition:
            (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
            {
                fadeOut(animationSpec = tween(700))
            },
        popEnterTransition:
            (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
            enterTransition,
        popExitTransition:
            (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
            exitTransition,
        sizeTransform:
            (@JvmSuppressWildcards
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? =
            null,
        builder: NavGraphBuilder.() -> Unit
    )
    ```

    - navController : 화면을 이동시키는 컨트롤러
    - startDestination : NavHost가 제일 처음 띄워줄 화면
    - builder : navigation으로 움직일 곳들(graph)들을 설정하는 곳
    - 화면전환 애니메이션 관련 매개변수

1. Destination 설정
    1. 옮길 데이터 없는 경우

        ```kotlin
        @Serializable // 앞서 말했듯 직렬화를 해주고
        data object A // 경로의 이름을 지어요
        
        // A로 이동시 띄울 화면을 선언해요
        @Composable
        fun AScreen(
            paddingValues: PaddingValues,
            navigateToB: (name: String) -> Unit,
        ) {
        
        }
        ```

    2. 파라미터가 필요한 경우

        ```kotlin
        @Serializable
        data class B( // 파라미터가 필요하다면 이렇게 넣어줘요
            val name: String
        )
        
        // B로 이동시 띄울 화면을 선언해요
        @Composable
        fun BScreen(
            paddingValues: PaddingValues,
            name: String,
            navigateToC: (id: String, password: String) -> Unit,
        ) {
        
        }
        
        @Serializable
        data class C(
            val id: String,
            val password: String
        )
        
        @Composable
        fun CScreen(
            paddingValues: PaddingValues,
            c: C // 이렇게 묶어서도 파라미터로 넣어줄 수 있어요!
        ) {
        
        }
        ```


1. NavHost에 경로 연결
    1. backStackEntry 활용

        ```kotlin
        NavHost(
        	navController = navController,
        	startDestination = A
        ) {
        	composable<A> {
        		AScreen(
        			paddingValues = innerPadding,
        			navigateToB = { name ->
        				navController.navigate(B(name))
        			}
        		)
        	}
        
        	composable<B> { backStackEntry ->
        		val item = backStackEntry.toRoute<B>()
        		BScreen(
        			paddingValues = innerPadding,
        			name = item.name,
        			navigateToC = { id, password ->
        				navController.navigate(C(id, password))
        			}
        		)
        	}
        
        	composable<C> { backStackEntry ->
        		val item = backStackEntry.toRoute<C>()
        		CScreen(
        			paddingValues = innerPadding,
        			c = item
        		)
        	}
        }
        ```

        - composable<> 안에 Destination 설정
        - backStackEntry를 이용해서 파라미터 값 받아오기
    2. viewModel 활용

        ```kotlin
        class DViewModel(
            savedStateHandle: SavedStateHandle, // 이 친구가 핵심이에요!
        ): ViewModel() {
            val profile = savedStateHandle.toRoute<D>()
        }
        
        @Composable
        fun DScreen(
            paddingValues: PaddingValues,
            viewModel: DViewModel = viewModel(),
        ) {
            val profile = remember { viewModel.profile }
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Hello, my ID is ${profile.id}!\nmy password is ${profile.password}")
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        ```

        - viewModel에서 savedStateHandle로 바로 값에 접근 가능

1. 화면 이동

    ```kotlin
    navController.navigate(A)
    ```


### (2) Stack

- navigate 함수의 navOptions로 활용

1. popUpTo

    ```kotlin
    navigateToB = { name ->
    	val navOptions = navOptions {
    		popUpTo(A)
    	}
    
    	navController.navigate(C(id, pw), navOptions)
    }
    ```

    - C로 이동하기 전, 백스텍에서 A까지의 스택을 꺼냄
    - A → B → C로 이동했다면 : popUpTo<A> 이후 C에 도착했을 때의 스택은 A → C

1. inclusive

    ```kotlin
    navigateToB = { name ->
    	val navOptions = navOptions {
    		popUpTo<B> {
    			inclusive = true
    		}
    	}
    
    	navController.navigate(D(id, pw), navOptions)
    }
    ```

    - popUpTo에서 본인을 포함해서 스택을 꺼냄
    - A→B→C→D로 이동했다면 : popUpTo<B> 이후에는 A → D

1. saveState & restoreState

    ```kotlin
    navigateToB = { name ->
    	val navOptions = navOptions {
    		popUpTo<B> {
    			saveState = true
    		}
    		
    		restoreState = true
    	}
    
    	navController.navigate(D(id, pw), navOptions)
    }
    ```

    - saveState를 사용한다면 현재 페이지의 상태를 저장할 수 있음
    - restoreState를 사용한다면 저장한 페이지의 정보를 불러올 수 있음

1. launchSingleTop

    ```kotlin
    navigateToB = { name ->
    	val navOptions = navOptions {
    		launchSingleTop = true
    	}
    
    	navController.navigate(C(id, pw), navOptions)
    }
    ```

    - 싱글탑