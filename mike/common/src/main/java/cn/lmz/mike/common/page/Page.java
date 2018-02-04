package cn.lmz.mike.common.page;


import cn.lmz.mike.common.V;

public  class Page {
   private int  intPageSize= V.PAGESIZE;
   private int  intRowCount=0;   
   private int  intPageCount=0;  
   private int  intCurrentPage=1;  
   private int  startNum=1;
   
    public void init(int rcount,int cPage){
        intRowCount = rcount;
        intCurrentPage=cPage;
        intPageCount=(intRowCount+intPageSize-1)/intPageSize;

        if(intCurrentPage > intPageCount){
            //intCurrentPage = intPageCount;
        }
        if(intPageCount < 1){
            intCurrentPage = 1;
        }
        startNum=(intCurrentPage-1)*intPageSize;     
    }
	public int getIntPageSize() {
		return intPageSize;
	}
	public void setIntPageSize(int intPageSize) {
		this.intPageSize = intPageSize;
	}
	public int getIntRowCount() {
		return intRowCount;
	}
	public void setIntRowCount(int intRowCount) {
		this.intRowCount = intRowCount;
	}
	public int getIntPageCount() {
		return intPageCount;
	}
	public void setIntPageCount(int intPageCount) {
		this.intPageCount = intPageCount;
	}
	public int getIntCurrentPage() {
		return intCurrentPage;
	}
	public void setIntCurrentPage(int intCurrentPage) {
		this.intCurrentPage = intCurrentPage;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
    
    
}
