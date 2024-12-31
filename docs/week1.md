## 노션 링크

https://marchbreeze.notion.site/UI-16cb6895dba98005b450dbb60d210c7d?pvs=4

## 1. 명령형 UI vs 선언형 UI

- 명령형(XML)
    - 절차 지향적: 개발자가 UI를 “어떻게” 업데이트할지를 단계별로(명령으로) 직접 지정
    - 상태 변경 시 직접 갱신: 개발자가 UI 요소의 상태를 직접 제어 (setter 메서드)
    - 개발자들이 함수를 통해서 하나하나 수동으로 뷰를 조작 → 이는 오류 발생 가능성을 크게 만들

- 선언형(Compose)
    - 선언적: “어떻게 UI를 갱신할지”를 일일이 명령하지 않고, “현재 상태가 이러하니 UI는 이렇게 보여야 한다”고 선언
    - 상태 기반 UI: UI는 state와 항상 동기화되어 있으며, 상태가 바뀌면 자동으로 UI가 재구성
    - 재구성: 상태가 달라지면 필요한 부분의 UI를 다시 그려주므로, 개발자는 UI 로직을 직접 업데이트하는 코드를 크게 줄일 수 있음

- 비교

  | **구분** | **명령형 UI** | **선언형 UI** |
      | --- | --- | --- |
  | 개념 | 함수의 호출을 통해 명령을 내리는 것을 의미 | UI 상태와 레이아웃을 설명하는 구조화된 데이터나 코드를 사용해 UI를 정의 |
  | UI 렌더링 방식 | 객체의 상태를 XML에 바인딩 | 메서드를 호출하는 방식으로 UI를 렌더링 |
  | UI 업데이트 방식 | setter 메서드 사용 | 단순히 함수를 호출하는 형태로 UI를 구성 |
  | 장점 | 정밀한 제어 가능 | 코드 가독성 향상
    유지 관리 용이 |
  | 단점 | 코드 복잡성 증가
    상태 관리 어려움 | UI 표현 방식에 대한 제약 |

## 2. 컴포즈 기본 컴포넌트

### (1) Text

- 예시

    ```kotlin
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    ```


- 파라미터

    ```kotlin
    @Composable
    fun Text(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontStyle: FontStyle? = null,
        fontWeight: FontWeight? = null,
        fontFamily: FontFamily? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null,
        style: TextStyle = LocalTextStyle.current
    )
    ```

    - overflow : 텍스트가 지정된 영역을 넘어갈 경우 처리 방식을 지정
    - softWrap : 텍스트가 영역을 넘어갈 때 자동으로 줄바꿈(랩핑)할지 여부를 지정
    - onTextLayout : 텍스트가 실제로 레이아웃된 결과를 콜백으로 받아볼 수 있는 함수 - 정보 확인

### (2) Button

- 예시

    ```kotlin
    Button(
        onClick = { /* 클릭 시 수행될 동작 */ },
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Click Me", color = Color.White) // 버튼 내부에 들어갈 컴포넌트
    }
    ```


- 파라미터

    ```kotlin
    @Composable
    fun Button(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        shape: Shape = ButtonDefaults.shape,
        colors: ButtonColors = ButtonDefaults.buttonColors(),
        elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
        border: BorderStroke? = null,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        content: @Composable RowScope.() -> Unit
    )
    ```

    - shape : RectangleShape, CircleShape, RoundedCornerShape, CutCornerShape
    - border : 버튼의 테두리 적용 가능 `ex. border = BorderStroke(1.dp, Color.Blue)`
    - contentPadding : 버튼 안쪽 내용의 패딩 `ex. PaddingValues(horizontal = 12.dp)`
    - interactionSource : 버튼이 클릭되거나 눌렸을 때, 포커스를 받았을 때 등의 상태를 관리
    - content : RowScope 형태로 제공

### (3) TextField

- 예시

    ```kotlin
    var text by remember { mutableStateOf("") }
    
    TextField(
    	value = text,
    	onValueChange = { text = it },
    	modifier = Modifier
    		.fillMaxWidth()
    		.padding(10.dp),
    	label = { Text("Enter your name") },
    	placeholder = { Text("ex) JiHyun Bae") },
    	leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "User Icon") },
    	singleLine = true,
    )
    ```


- 파라미터

    ```kotlin
    @Composable
    fun TextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = LocalTextStyle.current,
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        prefix: @Composable (() -> Unit)? = null,
        suffix: @Composable (() -> Unit)? = null,
        supportingText: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        singleLine: Boolean = false,
        maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
        minLines: Int = 1,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = TextFieldDefaults.shape,
        colors: TextFieldColors = TextFieldDefaults.colors()
    ) 
    ```

    - value : TextField에 표시될 텍스트 (TextFieldValue로 커서 위치, 텍스트 선택 범위 등 포함 가능)
    - onValueChange : 변경된 문자열 콜백 전달 - 상태를 직접 갱신해줘야 리컴포지션 진행
    - label : TextField 상단(또는 내부)에 표시될 **라벨**
    - placeHolder : 힌트 텍스트
    - leadingIcon / trailingIcon : 아이콘, 버튼 배치
    - prefix / suffix : 입력되는 텍스트 앞·뒤에 붙일 정적 컴포저블 - 화폐 단위 등
    - supportingText : 보조 설명(에러 메세지, 가이드 텍스트 등)을 표시
    - visualTransformation : ex. 비밀번호 입력 시 PasswordVisualTransformation()을 적용해 문자 대신 ‘●’로 표시
    - interactionSource : TextField의 눌리거나 포커스를 받는 등의 상태 추적

## 3. 컴포즈 레이아웃 시스템 방식

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/b5324c51-5ee8-4ca0-840a-d493ffc9b342/image.png)

### (1) Composition

> “**어떤 요소를 어떻게 배치하고 그릴 것인지**”를 선언하는 **UI 트리**를 만들어 내는 단계
>

1. 컴포저블 함수 실행 (Recomposition)
    - @Composable로 표시된 함수들을 **실행**하여, UI 트리(컴포지션)를 생성
    - 컴포저블 내부에서 다른 컴포저블을 호출하면서, 계층구조(트리 형태)의 화면 구성을 선언적으로 기술
    - Recomposition이 필요한 시점에 발생해, 필요한 부분만 다시 컴포저블 함수를 실행하여 화면 갱신

1. UI 트리 구축
    - 컴포즈 런타임은 컴포저블 함수를 통해 **UI 요소와 상태 간의 관계**를 파악하고, 트리 구조로 정리
    - 불필요한 재구성 최소화
        1. 멱등성
            - 연산을 여러 번 적용해도 결과가 달라지지 않는 성질
                - 입력(파라미터, remember 상태 등)이 동일하다면 항상 같은 UI 결과를 만들어야 함
            - smart-recomposition: 입력이 동일하면 재컴포지션을 건너뛰거나, 꼭 필요한 부분만 새로 그릴 수 있어 퍼포먼스가 좋아짐
            - 어떤 순서로 컴포저블이 재실행될지를 개발자가 제어할 수 없음

              → 컴포즈 런타임이 최적의 타이밍에 컴포저블들을 다시 호출하기 때문에, 언제 몇 번 호출해도 결과가 같도록 만드는 멱등성이 매우 중요

        2. 위치 정보 기반 메모화
            - “컴포저블이 어느 위치에 대응되는가”를 기준으로, 같은 입력을 넣었을 때 동일한 출력을 보장
            - 내부적으로 컴포저블을 트리 구조로 배치하고, 각 컴포저블 노드에 위치 키를 부여해 관리
                - ex. LazyColumn에 10개의 아이템이 있고, 그 중 다섯 번째 아이템을 업데이트할 때 Compose는 “5번째 아이템의 컴포저블 위치
                  키”로 해당 아이템을 식별

### (2) Layout

> UI 트리의 각 요소에 대해 **크기·위치를 계산**하여, “어디에, 얼마만큼 그릴 것인가?”를 정하는 단계
>

1. 측정
    - 만들어진 UI 트리를 바탕으로, 자식 컴포저블들이 “얼마나 공간을 필요로 하는지”를 계산
    - 각 컴포저블은 부모 컴포저블이 준 제약을 바탕으로 자신의 크기를 측정하고, 필요에 따라 자식들에게도 제약을 넘겨 재측정
    - 하위 요소를 “한번만” 측정하도록 하여 높은 성능 발휘 (↔ intrinsic 내장 기능으로 여러번 측정 가능)

1. 배치
    - 크기가 정해진 각 컴포저블(또는 레이아웃)은 실제로 부모 안에서 **어디에 위치할지**(좌표)를 결정
        - ex. Row 컴포저블 : 자식들을 가로 혹은 세로로 순서대로 배치하고, 각 자식의 x, y 좌표를 결정
    - 최종적으로 모든 자식 컴포저블이 화면 좌표계(혹은 부모의 좌표계) 상에서 어느 위치에 어떤 크기로 그려질지 확정

      ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/9a3b15b4-4262-4cdc-800c-1427ac5a6fbc/image.png)

### (3) Drawing

> “**실제로 픽셀을 그리는 시점**”으로, 화면에 보이는 모든 시각적 요소를 최종 완성하는 단계
>

1. 렌더링
    - Layout 단계에서 결정된 “크기·위치 정보”를 토대로, 각 컴포저블을 화면에 그리는(Draw) 단계
    - 내부적으로 Canvas 기반의 드로잉 메커니즘을 사용하여, 배경색, 텍스트, 아이콘, 도형 등의 픽셀을 실제로 화면 상에 표현
    - “어떤 색상/테두리/둥근 모서리” 등이 적용되어야 하는지 컴포저블 별로 확인하여, 화면에 최종 픽셀 배치

1. 애니메이션 & UI 효과
    - 상태에 따라 애니메이션이 있다면, Compose는 재컴포지션 또는 별도의 드로잉 업데이트를 통해 프레임 단위로 화면을 갱신
    - 시각적 변화를 최종적으로 사용자에게 보여주는 단계

## 4. 컴포즈 기본 레이아웃

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/be427384-70b5-4c9a-bbc0-84ac5ed9f203/image.png)

1. Column

    ```kotlin
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("First Item")
        Text("Second Item")
    }
    ```

    1. horizontalAlignment (수평 정렬)
        - 수평 상 Start, CenterHorizontally, End 정렬
    2. verticalArrangement (수직 배치)

       ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/0c45c813-901b-4286-a79f-ce99e4dcf635/image.png)


1. Row

    ```kotlin
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Left Side")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Right Side")
    }
    ```

    1. verticalAlignment (수직 정렬)
        - 수직 상 Top, CenterVertically, Bottom 정렬
    2. horizontalArrangement (수평 배치)

       ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/edfd69d1-6c01-4d0c-9269-1bae8a4e3915/3f2ea6b2-cacb-4419-9aeb-0036488c4981/image.png)


1. Box

    ```kotlin
    Box {
        Image(painterResource(R.drawable.ic_launcher_foreground), contentDescription = "test image")
        Icon(Icons.Filled.Check, contentDescription = "Check mark")
    }
    ```

    - 여러 컴포넌트를 겹쳐서 배치할 수 있는 컴포저블

    1. contentAlignment

        ```kotlin
        @Stable
        val TopStart: Alignment = BiasAlignment(-1f, -1f)
        @Stable
        val TopCenter: Alignment = BiasAlignment(0f, -1f)
        @Stable
        val TopEnd: Alignment = BiasAlignment(1f, -1f)
        @Stable
        val CenterStart: Alignment = BiasAlignment(-1f, 0f)
        @Stable
        val Center: Alignment = BiasAlignment(0f, 0f)
        @Stable
        val CenterEnd: Alignment = BiasAlignment(1f, 0f)
        @Stable
        val BottomStart: Alignment = BiasAlignment(-1f, 1f)
        @Stable
        val BottomCenter: Alignment = BiasAlignment(0f, 1f)
        @Stable
        val BottomEnd: Alignment = BiasAlignment(1f, 1f)
        ```

## 5. 모디파이어

- Modifier
    - 컴포넌트가 화면에 어떻게 배치되고, 어떻게 보이며, 어떤 상호작용을 할 수 있는지를 선언적으로 지정
    - 컴포저블을 꾸미고(Decoration), 레이아웃(배치)하고, 제스처 등을 처리하는 데 사용되는 체이너블(chained) 인터페이스

1. 레이아웃 관련

   | 확장 함수 | 용도 |
       | --- | --- |
   | **size(width: Dp, height: Dp)** / **size(size: DpSize)** | 컴포넌트의 고정 크기를 **width × height**로 지정 |
   | **width(width: Dp)** / **height(height: Dp)** | 너비나 높이를 별도로 지정 |
   | **fillMaxSize(fraction: Float = 1f)** | 부모 컴포저블의 가용 크기를 **(fraction) 배율**만큼 채움 |
   | **fillMaxWidth(fraction: Float = 1f)** | 부모 가용 폭을 **(fraction) 배율**만큼 채움 |
   | **fillMaxHeight(fraction: Float = 1f)** | 부모 가용 높이를 **(fraction) 배율**만큼 채움 |
   | **padding(...)** | 내부 콘텐츠와의 **여백(padding)** 설정 |
   | **offset(x: Dp = 0.dp, y: Dp = 0.dp)** | 레이아웃에서 **시각적으로** (x, y)만큼 이동 (공간은 원래 자리 차지) |
   | **wrapContentSize(align: Alignment)** | 자식 크기에 맞춰 **최소 공간**만 사용하고, 정렬을 지정 |
   | **requiredSize(...)** / **requiredWidth(...)** | 부모 제약을 무시하고, **강제로** 크기를 적용 |

2. 디자인 / 데코레이션

   | 확장 함수 | 용도 |
       | --- | --- |
   | **background(color: Color, shape: Shape)** | 컴포넌트에 **배경색**과 모양(Shape)을 적용 |
   | **border(width: Dp, color: Color, shape: Shape)** | 테두리(Border)를 그리고, 모양(Shape)을 설정 |
   | **clip(shape: Shape)** | 내부 콘텐츠를 특정 모양(Shape)으로 **클리핑** |
   | **shadow(elevation: Dp, shape: Shape, clip: Boolean = false)** | **그림자(음영)** 효과 적용 |
   | **alpha(alpha: Float)** | 컴포넌트 전체의 **투명도**(0.0 ~ 1.0) |

3. 입력 / 제스처

   | 확장 함수 | 용도 |
       | --- | --- |
   | **clickable(onClick: () -> Unit)** | 터치(클릭) 이벤트 처리 |
   | **pointerInput(key1: Any?, block: suspend PointerInputScope.() -> Unit)** | **커스텀 제스처**(드래그, 핀치 등)를 처리할 때 사용 |
   | **draggable(...)** | 드래그 동작을 처리, 드래그 거리를 추적해 **상태(state)** 업데이트 |
   | **scrollable(...)** | 스크롤 동작 처리, **세로/가로** 방향으로 스크롤 가능 |
   | **combinedClickable(...)** | 클릭/롱클릭(long press) 등 **여러 제스처**를 함께 처리 |

4. 기타 / 고급

   | 확장 함수 | 용도 |
       | --- | --- |
   | **onGloballyPositioned { coordinates: LayoutCoordinates -> ... }** | 컴포넌트 배치 후, **절대 좌표**나 **크기 정보**를 얻을 때 사용 |
   | **semantics { ... }** | **접근성(Accessibility)** 정보 추가 (스크린리더용 레이블, 속성 등) |
   | **graphicsLayer(block: GraphicsLayerScope.() -> Unit)** | 하드웨어 가속 기반으로 회전, 확대/축소, 알파(투명도) 등을 처리 |
   | **animateContentSize()** | 자식의 크기가 변할 때 **애니메이션**으로 자연스럽게 변화 |

## 6. Dialog, Snackbar, Toast

1. Dialog

    ```kotlin
    // 다이얼로그의 표시 여부를 나타내는 상태값
    val showDialog = remember { mutableStateOf(false) }
    
    // 버튼으로 다이얼로그 열기
    Button(
        onClick = { showDialog.value = true },
    ) {
        Text("다이얼로그!!", color = Color.White)
    }
    
    // 다이얼로그 조건부 표시
    if (showDialog.value) {
        Dialog(
    		    // 다이얼로그 영역 밖을 터치하거나 뒤로가기 키를 눌렀을 때 호출되는 콜백
            onDismissRequest = { showDialog.value = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    text = "안드짱"
                )
                Spacer(modifier = Modifier.height(36.dp))
                Button(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { showDialog.value = false },
                    enabled = true
                ) {
                    Text("확인", color = Color.White)
                }
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
    ```


1. snackbar

    ```kotlin
    // UI 스코프 활용 (스낵바는 코루틴 필요)
    val scope = rememberCoroutineScope()
    
    // 스낵바의 상태(열림/닫힘, 메시지, 액션 등)를 관리
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Scaffold : 스낵바, 앱바, FAB 등을 편리하게 배치하기 위한 레이아웃 컴포넌트
    Scaffold(
    		// snackbarHostState가 관리하는 스낵바 메시지를 화면에 표시
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Person, contentDescription = "") },
                onClick = {
    		            // 메시지가 snackbarHostState에 전달되어 실제로 화면에 표시
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


1. toast

    ```kotlin
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = { Toast.makeText(context, "🍞토스트", Toast.LENGTH_SHORT).show() }
        ) {
            Text("토스트 테스트")
        }
    }
    ```