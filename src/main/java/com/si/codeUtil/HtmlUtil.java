package com.si.codeUtil;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/*
* 类描述：实现动态生成html页面代码
* @auther linzf
* @create 2017/10/10 0010 
*/
public class HtmlUtil {

    /**
     * 功能描述：生成HTML页面模板
     * @param author 创建者
     * @param table 表实体
     * @param beanName 实体名字
     */
    public static String genHtml(String author, TableModel table,String beanName){
        StringBuffer sb = new StringBuffer();
        List<ColumnModel> columnModelList = table.getColumns();
        // 查询form的id
        String queryFormId = "query"+beanName;
        // 字段备注
        String remark = "";
        // 字段名称
        String type = "";
        // 展示的分页table的名称
        String tableName = beanName+"-table";
        sb.append("<html xmlns:th=\"http://www.thymeleaf.org\"\n xmlns:sec=\"http://www.thymeleaf.org/thymeleaf-extras-springsecurity4\"> \n");
        sb.append("<head th:include=\"include/includebase\"></head> \n");
        sb.append("<body> \n");
        sb.append("<div class=\"panel-body\" style=\"padding-bottom:0px;\"> \n");
        sb.append("<div class=\"panel panel-default\">\n");
        sb.append("<div class=\"panel-heading\">查询条件</div>\n");
        sb.append("<div class=\"panel-body\">\n");
        sb.append("<form class=\"form-inline\" role=\"form\" style=\"float: left; width: 100%;margin-left: 20px;\" method=\"post\" id=\""+queryFormId+"\">\n");
        // 遍历循环整个表的实体
        for (ColumnModel columnModel : columnModelList) {
            sb.append("<div class=\"form-group\">\n");
            // 获取字段类型
            type = columnModel.getFieldName();
            // 获取表字段的备注
            if(StringUtils.isNotBlank(columnModel.getRemarks())){
                remark = columnModel.getRemarks();
            }else{
                remark = type;
            }
            sb.append("<label for=\"type\">"+remark+":</label>\n");
            sb.append("<input type=\"text\" class=\"form-control\" name=\""+type+"\" id=\""+type+"\"  placeholder=\"请输入"+remark+"\" />\n");
            sb.append("</div>\n");
        }
        sb.append("</form>\n");
        sb.append("</div>\n");
        sb.append("</div>\n");
        sb.append("</div><table id=\""+tableName+"\" style=\"margin-top: -50px;\">\n");
        sb.append("</table>\n");
        sb.append("</div>\n");
        // JavaScript执行脚本创建的开始
        sb.append("<script th:inline=\"javascript\">\n");
        sb.append("$(function() {\n");
        sb.append("initTable();\n");
        //sb.append("$('#"+tableName+"').bootstrapTable('hideColumn', 'id');");
        sb.append("});\n");
        sb.append("function doQuery(){\n");
        sb.append(" $('#"+tableName+"').bootstrapTable('refresh');    //刷新表格\n");
        sb.append("}\n");
        sb.append("function initTable(){\n");
        sb.append("$('#"+tableName+"').bootstrapTable({\n");
        sb.append("url:\""+beanName+"/list\",\n");
        sb.append("height: $(window.parent.document).find(\"#wrapper\").height() - 252,\n");
        sb.append("width:$(window).width(),\n");
        sb.append("showColumns:true,\n");
        sb.append("formId :\""+queryFormId+"\",\n");
        sb.append("pagination : true,\n");
        sb.append("sortName : 'id',\n");
        sb.append("sortOrder : 'desc',\n");
        sb.append("clickToSelect: true,// 单击某一行的时候选中某一条记录\n");
        sb.append("pageSize : 13,\n");
        sb.append("toolbars:[\n");
        sb.append("{\n");
        sb.append("text: '添加',\n");
        sb.append("iconCls: 'glyphicon glyphicon-plus',\n");
        sb.append("handler: function () {\n");
        sb.append("window.Ewin.dialog({title:\"添加\",url:\""+beanName+"/addPage\",width:400,height:500})\n");
        sb.append("}\n");
        sb.append("},\n");
        sb.append("{\n");
        sb.append("text: '修改',\n");
        sb.append("iconCls: 'glyphicon glyphicon-pencil',\n");
        sb.append("handler: function () {\n");
        sb.append("var rows = $('#"+tableName+"').bootstrapTable('getSelections');\n");
        sb.append(" if(rows.length==0||rows.length>1){\n");
        sb.append("window.Ewin.alert({message:'请选择一条需要修改的数据!'});\n");
        sb.append(" return false;\n");
        sb.append("}\n");
        sb.append("window.Ewin.dialog({title:\"修改\",url:\""+beanName+"/updatePage?id=\"+rows[0].id,width:400,height:500});\n");
        sb.append("}\n");
        sb.append("},\n");
        sb.append("{\n");
        sb.append("text: '删除',\n");
        sb.append("iconCls: 'glyphicon glyphicon-remove',\n");
        sb.append("handler: function () {\n");
        sb.append("var rows = $('#"+tableName+"e').bootstrapTable('getSelections');\n");
        sb.append("if(rows.length==0){\n");
        sb.append("window.Ewin.alert({message:'请选择一条需要删除的数据!'});\n");
        sb.append("return false;\n");
        sb.append("}\n");
        sb.append(" window.Ewin.confirm({title:'提示',message:'是否要删除您所选择的记录？',width:500}).on(function (e) {\n");
        sb.append("if (e) {\n");
        sb.append("$.post(\""+beanName+"/removeBath\",{json:JSON.stringify(rows)},function(e){\n");
        sb.append("if(e.result){\n");
        sb.append("window.Ewin.alert({message:e.msg});\n");
        sb.append(" doQuery();\n");
        sb.append(" }\n");
        sb.append(" });\n");
        sb.append("}\n");
        sb.append(" });\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append(" ],\n");
        sb.append("columns: [\n");
        sb.append("{\n");
        sb.append("checkbox: true\n");
        sb.append("},\n");
        sb.append("{\n");
        sb.append("field: '',\n");
        sb.append("title: '序号',\n");
        sb.append("formatter: function (value, row, index) {\n");
        sb.append(" return index+1;\n");
        sb.append("}\n");
        sb.append("},\n");
        // 遍历循环整个表的实体
        for (int i=0;i<columnModelList.size();i++) {
            ColumnModel columnModel = columnModelList.get(i);
            // 获取字段类型
            type = columnModel.getFieldName();
            // 获取表字段的备注
            if(StringUtils.isNotBlank(columnModel.getRemarks())){
                remark = columnModel.getRemarks();
            }else{
                remark = type;
            }
            sb.append("{\n");
            sb.append("field : '"+type+"',\n");
            sb.append("title : '"+remark+"',\n");
            sb.append("align : 'center',\n");
            sb.append("valign : 'middle',\n");
            sb.append("sortable : true\n");
            if(i==columnModelList.size()){
                sb.append(" }\n");
            }else{
                sb.append(" },\n");
            }

        }
        sb.append("]\n");
        sb.append(" });\n");
        sb.append("}\n");
        sb.append("</script>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

}
