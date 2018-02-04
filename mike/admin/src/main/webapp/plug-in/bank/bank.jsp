<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<link  href="${pageContext.request.contextPath}/plug-in/bank/bank.css"  rel="stylesheet" type="text/css" />

					<div id="bankdiv">
						<div class="a_CMB"><input type="radio" name="bank" value="CMB-DEBIT" checked/><a href="#" class="a_CMB"></a></div>
						<div class="a_ABC"><input type="radio" name="bank" value="ABC" /><a href="#" class="a_ABC"></a></div>
						<div class="a_CCB"><input type="radio" name="bank" value="CCB-DEBIT" /><a href="#" class="a_CCB"></a></div>
						<div class="a_SPDB"><input type="radio" name="bank" value="SPDB-DEBIT" /><a href="#" class="a_SPDB"></a></div>
						<div class="a_BOC"><input type="radio" name="bank" value="BOC-DEBIT" /><a href="#" class="a_BOC"></a></div>		
						<div class="a_ICBC"><input type="radio" name="bank" value="ICBC-DEBIT" /><a href="#" class="a_ICBC"></a></div>
						
						<div class="a_CIB"><input type="radio" name="bank" value="CIB" /><a href="#" class="a_CIB"></a></div>
						<div class="a_GDB"><input type="radio" name="bank" value="GDB-DEBIT" /><a href="#" class="a_GDB"></a></div>
						<div class="a_CMBC"><input type="radio" name="bank" value="CMBC" /><a href="#" class="a_CMBC"></a></div>
						<div class="a_HZCB"><input type="radio" name="bank" value="HZCB" /><a href="#" class="a_HZCB"></a></div>
						<div class="a_CEB"><input type="radio" name="bank" value="CEB-DEBIT" /><a href="#" class="a_CEB"></a></div>
						<div class="a_SHBANK"><input type="radio" name="bank" value="SHBANK" /><a href="#" class="a_SHBANK"></a></div>
						
						<div class="a_NBBANK"><input type="radio" name="bank" value="NBBANK" /><a href="#" class="a_NBBANK"></a></div>
						<div class="a_SPABANK"><input type="radio" name="bank" value="SPA-DEBIT" /><a href="#" class="a_SPABANK"></a></div>
						<div class="a_BJRCB"><input type="radio" name="bank" value="BJRCB" /><a href="#" class="a_BJRCB"></a></div>
						<div class="a_FDB"><input type="radio" name="bank" value="FDB" /><a href="#" class="a_FDB"></a></div>
						<div class="a_PSBC"><input type="radio" name="bank" value="PSBC-DEBIT" /><a href="#" class="a_PSBC"></a></div>
						<div class="a_COMM"><input type="radio" name="bank" value="COMM-DEBIT" /><a href="#" class="a_COMM"></a></div>
						
						<div class="a_BJBANK"><input type="radio" name="bank" value="BJBANK" /><a href="#" class="a_BJBANK"></a></div>
						<div class="a_SHRCB"><input type="radio" name="bank" value="SHRCB" /><a href="#" class="a_SHRCB"></a></div>
						<div class="a_CITIC"><input type="radio" name="bank" value="CITIC-DEBIT" /><a href="#" class="a_CITIC"></a></div>
					</div>
					<div id="btndiv" class="btn" >
						<a class="yellow_btn" href='javascript:void(0);' onclick="paybank()"  target="_blank">确认</a>
					</div>
					
					<script type="text/javascript">
						function paybank(){
							var bank=$("#bankdiv").find("input[type='radio']:checked").val();
							if(bank==undefined||bank==null||bank==''){
								alert('请选择支付银行');
							}else{
								api.onPay('<s:property value="#ord.id"/>',bank);
							}
						}
					</script>
