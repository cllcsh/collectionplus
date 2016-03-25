<div class="rhead">
	<div class="rpos">
		<s:iterator id="func" value="#funcNode.parents" status="sta">
			<s:if test="%{#sta.index > 0}"><a href='<%=path%><s:property value="#func.link"/>'></s:if><s:property value="#func.name"/><s:if test="%{#sta.index > 0}"></a></s:if>>>
		</s:iterator>
		<s:property value="#funcNode.name"/>
	</div>
	<div class="clear"></div>
</div>