<%@page contentType="text/html; charset=UTF-8"%>
<!-- 车辆手续 -->
<s:if test="%{carInfo.procedures != 0}">
	<div id="tab1" class="mod-buy-tab1 mod-buy-tab">
		<div class="mod-buy-tab-title">
			<h2>车辆手续</h2>
		</div>
		<div class="inner">
			<div class="row">
				<p>
					因排放标准或政策原因不能上牌？收藏网承诺向购车用户提供所购车辆办理牌照所需的全部合法手续！
				</p>
				<p>
					收藏网所售车辆，均为保税港口进口，合法完税，并经由海关商检、检验、检疫证明，手续完备，质量可靠，是合法进口的车辆。
				</p>
				<s:if test="%{(carInfo.procedures & 1) == 1}">
					<img src="/portal/img/procedure/1.png" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 2) == 2}">
					<img src="/portal/img/procedure/2.png" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 4) == 4}">
					<img src="/portal/img/procedure/4.png" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 8) == 8}">
					<img src="/portal/img/procedure/8.png" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 16) == 16}">
					<img src="/portal/img/procedure/16.png" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 32) == 32}">
					<img src="/portal/img/procedure/32.png" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 64) == 64}">
					<img src="/portal/img/procedure/64.jpg" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 128) == 128}">
					<img src="/portal/img/procedure/128.jpg" alt="">
				</s:if>
				<s:if test="%{(carInfo.procedures & 256) == 256}">
					<img src="/portal/img/procedure/256.jpg" alt="">
				</s:if>
	<!-- 			<div class="col col-6"> -->
	<!-- 				<div class="item"> -->
	<!-- 					<div class="img"></div> -->
	<!-- 					<div class="desc"> -->
	<!-- 						<h3>根据国家法律法规要求，手续为:</h3> -->
	<!-- 						<ol> -->
	<!-- 							<li>到后来也会有团从之前刚开始都是一些很入门的话</li> -->
	<!-- 							<li>从之前刚开始都是一些很入门的话题，到后来也会有团</li> -->
	<!-- 							<li>从之前刚开始都是一些很入门的话题，到后来也会有团</li> -->
	<!-- 							<li>从之前刚开始都是一些很入门的话题，到后来也会有团</li> -->
	<!-- 						</ol> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 			<div class="col col-6"> -->
	<!-- 				<div class="item"> -->
	<!-- 					<div class="img"></div> -->
	<!-- 					<div class="desc"> -->
	<!-- 						<h3>根据国家法律法规要求，手续为:</h3> -->
	<!-- 						<p>到后来也会有团从之前刚开始都是一些很入门的话从之前刚开始都是一些很入门的话题，到后来也会有团门的话从之前刚开始都是一些很入</p> -->
	<!-- 					</div> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
			</div>
		</div>
	</div>
</s:if>
<!-- ./车辆手续 -->

<!-- 购车需知 -->
<div id="tab2" class="mod-buy-tab2 mod-buy-tab">
	<div class="mod-buy-tab-title">
		<h2>购车需知</h2>
	</div>
	<div class="inner">
		<div class="group">
			<div class="icon">
				<i class="comm-img mod-buy-phone"></i>
			</div>
			<div class="text">
				<h3 class="title">预约说明</h3>
				<p>登陆帐号，挑选中意车款与数量，完成在线支付（第三方支付）</p>
			</div>
		</div>
		<div class="group">
			<div class="icon">
				<i class="comm-img mod-buy-car"></i>
			</div>
			<div class="text">
				<h3 class="title">提车点</h3>
				<p>由收藏根据地区城市就近安排指定地点提车</p>
			</div>
		</div>
		<div class="group">
			<div class="icon">
				<i class="comm-img mod-buy-calendar"></i>
			</div>
			<div class="text">
				<h3 class="title">需知细则</h3>
				<p>					
					1.挑选车源：挑选中意车源，正确填写订单信息，等待卖家确认。<br>
					2.支付订金：客服将尽快与您进一步核实订单信息，确认无误后支付订金。<br>
					3.锁定车源：订金支付完成后，收藏平台将尽快准备车源及相关手续。<br>
					4.支付余款：收藏平台按照订单信息准备完成后，400客服会与您联系支付余款。<br>
					5.物流配送：收藏平台收到您的购车余款后，按照订单要求安排物流配送。<br>
					6.交易完成：您验收通过后，交易完成。<br>
				</p>
			</div>
		</div>
	</div>
</div>
<!-- ./购车需知 -->

<!-- 免责声明 -->
<div id="tab3" class="mod-buy-tab3 mod-buy-tab">
	<div class="mod-buy-tab-title">
		<h2>免责声明</h2>
	</div>
	<div class="inner">
		<p>以上所展示的信息由企业自行提供，内容的真实性、准确性和合法性由发布企业负责。收藏对此不承担任何保证责任</p>
	</div>
</div>
<!-- ./免责声明 -->

<!-- 购车承诺 -->
<div class="mod-buy-promise">
	<h3 class="title text-center">购车承诺</h3>
	<div class="row">
		<div class="col col-3">
			<div class="img">
				<i class="comm-img mod-buy-red-car"></i>
			</div>
			<h4 class="item-title">真实车源</h4>
			<p class="item-body">浏览信息，100%真实车源</p>
		</div>
		<div class="col col-3">
			<div class="img">
				<i class="comm-img mod-buy-red-user"></i>
			</div>
			<h4 class="item-title">认证商家</h4>
			<p class="item-body">严格核实企业的合法性、真实性</p>
		</div>
		<div class="col col-3">
			<div class="img">
				<i class="comm-img mod-buy-red-note"></i>
			</div>
			<h4 class="item-title">手续齐备</h4>
			<p class="item-body">机动车相关手续一应俱全</p>
		</div>
		<div class="col col-3">
			<div class="img">
				<i class="comm-img mod-buy-red-tools"></i>
			</div>
			<h4 class="item-title">售后三包</h4>
			<p class="item-body">按照国家质检总局的汽车三包政策</p>
		</div>
	</div>
</div>
<!-- ./购车承诺 -->