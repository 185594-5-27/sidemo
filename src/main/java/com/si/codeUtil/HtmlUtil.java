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
     * 功能描述：生成更新数据的html页面
     * @param author
     * @param table
     * @param beanName
     * @return
     */
    public static String genUpdateHtml(String author, TableModel table,String beanName){
        StringBuffer sb = new StringBuffer();
        List<ColumnModel> columnModelList = table.getColumns();
        // 表单ID
        String formId = beanName + "Form";
        // 字段备注
        String remark = "";
        // 字段名称
        String type = "";
        sb.append("<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:sec=\"http://www.thymeleaf.org/thymeleaf-extras-springsecurity4\">\n");
        sb.append("<body>\n");
        sb.append("<form id=\""+formId+"\"  role=\"form\" method=\"post\" action=\""+beanName+"/update\">\n");
        // 遍历循环整个表的实体
        for (ColumnModel columnModel : columnModelList) {
            // 获取字段类型
            type = columnModel.getFieldName();
            // 获取表字段的备注
            if(StringUtils.isNotBlank(columnModel.getRemarks())){
                remark = columnModel.getRemarks();
            }else{
                remark = type;
            }
            if(!columnModel.isPrimaryKey()|| !columnModel.isAutoIncrement()){
                sb.append("\t<div class=\"form-group\" >\n");
                sb.append("\t\t<label for=\""+type+"\">"+remark+":</label>\n");
                sb.append("\t\t<input type=\"text\" class=\"form-control\" name=\""+type+"\" id=\""+type+"\" th:value=\"${entity."+type+"}\" placeholder=\"请输入"+remark+"\" />\n");
                sb.append("\t</div>\n");
            }else{
                sb.append("\t\t<input type=\"hidden\" name=\""+type+"\" id=\""+type+"\" th:value=\"${entity."+type+"}\" />\n");
            }
        }
        sb.append("</form>\n");
        sb.append("<script th:inline=\"javascript\">\n");
        sb.append("\t<![CDATA[\n");
        sb.append("\t$(function () {\n");
        sb.append("\t\t$('#"+formId+"').bootstrapValidator({\n");
        sb.append("\t\t\tmessage: 'This value is not valid',\n");
        sb.append("\t\t\tfeedbackIcons: {\n");
        sb.append("\t\t\t\tvalid: 'glyphicon glyphicon-ok',\n");
        sb.append("\t\t\t\tinvalid: 'glyphicon glyphicon-remove',\n");
        sb.append("\t\t\t\tvalidating: 'glyphicon glyphicon-refresh'\n");
        sb.append("\t\t\t},\n");
        sb.append("\t\t\tfields: {\n");
        // 遍历循环整个表的实体
        for (int i=0;i<columnModelList.size();i++) {
            ColumnModel columnModel = columnModelList.get(i);
            if(!columnModel.isPrimaryKey()|| !columnModel.isAutoIncrement()){
                // 获取字段类型
                type = columnModel.getFieldName();
                // 获取表字段的备注
                if (StringUtils.isNotBlank(columnModel.getRemarks())) {
                    remark = columnModel.getRemarks();
                } else {
                    remark = type;
                }
                sb.append("\t\t\t\t"+type+":{\n");
                sb.append("\t\t\t\t\tmessage: '"+remark+"验证失败',\n");
                sb.append("\t\t\t\t\tvalidators: {\n");
                sb.append("\t\t\t\t\tnotEmpty: {\n");
                sb.append("\t\t\t\t\t\tmessage: '"+remark+"不能为空'\n");
                sb.append("\t\t\t\t\t}\n");
                sb.append("\t\t\t\t}\n");
                if(i==columnModelList.size()){
                    sb.append("\t\t\t}\n");
                }else{
                    sb.append("\t\t\t},\n");
                }
                sb.append("");
            }
        }
        sb.append("\t\t\t}\n");
        sb.append("\t\t})\n");
        sb.append("\t\t$(\"#btnOk\",window.top.document).click(function() {\n");
        sb.append("\t\t\tvar bootstrapValidator = $(\"#"+formId+"\", window.top.document).data('bootstrapValidator');\n");
        sb.append("\t\t\tbootstrapValidator.validate();\n");
        sb.append("\t\t\tif(bootstrapValidator.isValid()){\n");
        sb.append("\t\t\t\t$.post($(\"#"+formId+"\",window.top.document).attr('action'),$(\"#"+formId+"\",window.top.document).serialize(),function(e){\n");
        sb.append("\t\t\t\t\tif(e.result){\n");
        sb.append("\t\t\t\t\t\t$('.modal-dialog', window.top.document).parent('div').remove()\n");
        sb.append("\t\t\t\t\t\t$('body', window.top.document).find('.modal-backdrop').remove();\n");
        sb.append("\t\t\t\t\t\t$(window.parent.document).contents().find(\".tab-pane.fade.active.in iframe\")[0].contentWindow.doQuery();\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.alert({message:'增加数据成功!'});\n");
        sb.append("\t\t\t\t\t}else{\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.alert({message:'增加数据失败!'});\n");
        sb.append("\t\t\t\t\t}\n");
        sb.append("\t\t\t\t })\n");
        sb.append("\t\t\t}\n");
        sb.append("\t\t });\n");
        sb.append("\t})\n");
        sb.append("\t]]>\n");
        sb.append("</script>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

    /**
     * 功能描述：生成增加数据的html页面
     * @param author
     * @param table
     * @param beanName
     * @return
     */
    public static String genAddHtml(String author, TableModel table,String beanName){
        StringBuffer sb = new StringBuffer();
        List<ColumnModel> columnModelList = table.getColumns();
        // 表单ID
        String formId = beanName + "Form";
        // 字段备注
        String remark = "";
        // 字段名称
        String type = "";
        sb.append("<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:sec=\"http://www.thymeleaf.org/thymeleaf-extras-springsecurity4\">\n");
        sb.append("<body>\n");
        sb.append("<form id=\""+formId+"\"  role=\"form\" method=\"post\" action=\""+beanName+"/save\">\n");
        // 遍历循环整个表的实体
        for (ColumnModel columnModel : columnModelList) {
            if(!columnModel.isPrimaryKey()|| !columnModel.isAutoIncrement()){
                // 获取字段类型
                type = columnModel.getFieldName();
                // 获取表字段的备注
                if(StringUtils.isNotBlank(columnModel.getRemarks())){
                    remark = columnModel.getRemarks();
                }else{
                    remark = type;
                }
                sb.append("\t<div class=\"form-group\" >\n");
                sb.append("\t\t<label for=\""+type+"\">"+remark+":</label>\n");
                sb.append("\t\t<input type=\"text\" class=\"form-control\" name=\""+type+"\" id=\""+type+"\"  placeholder=\"请输入"+remark+"\" />\n");
                sb.append("\t</div>\n");
            }
        }
        sb.append("</form>\n");
        sb.append("<script th:inline=\"javascript\">\n");
        sb.append("\t<![CDATA[\n");
        sb.append("\t$(function () {\n");
        sb.append("\t\t$('#"+formId+"').bootstrapValidator({\n");
        sb.append("\t\t\tmessage: 'This value is not valid',\n");
        sb.append("\t\t\tfeedbackIcons: {\n");
        sb.append("\t\t\t\tvalid: 'glyphicon glyphicon-ok',\n");
        sb.append("\t\t\t\tinvalid: 'glyphicon glyphicon-remove',\n");
        sb.append("\t\t\t\tvalidating: 'glyphicon glyphicon-refresh'\n");
        sb.append("\t\t\t},\n");
        sb.append("\t\t\tfields: {\n");
        // 遍历循环整个表的实体
        for (int i=0;i<columnModelList.size();i++) {
            ColumnModel columnModel = columnModelList.get(i);
            if(!columnModel.isPrimaryKey()|| !columnModel.isAutoIncrement()){
                // 获取字段类型
                type = columnModel.getFieldName();
                // 获取表字段的备注
                if (StringUtils.isNotBlank(columnModel.getRemarks())) {
                    remark = columnModel.getRemarks();
                } else {
                    remark = type;
                }
                sb.append("\t\t\t\t"+type+":{\n");
                sb.append("\t\t\t\t\tmessage: '"+remark+"验证失败',\n");
                sb.append("\t\t\t\t\tvalidators: {\n");
                sb.append("\t\t\t\t\tnotEmpty: {\n");
                sb.append("\t\t\t\t\t\tmessage: '"+remark+"不能为空'\n");
                sb.append("\t\t\t\t\t}\n");
                sb.append("\t\t\t\t}\n");
                if(i==columnModelList.size()){
                    sb.append("\t\t\t}\n");
                }else{
                    sb.append("\t\t\t},\n");
                }
                sb.append("");
            }
        }
        sb.append("\t\t\t}\n");
        sb.append("\t\t})\n");
        sb.append("\t\t$(\"#btnOk\",window.top.document).click(function() {\n");
        sb.append("\t\t\tvar bootstrapValidator = $(\"#"+formId+"\", window.top.document).data('bootstrapValidator');\n");
        sb.append("\t\t\tbootstrapValidator.validate();\n");
        sb.append("\t\t\tif(bootstrapValidator.isValid()){\n");
        sb.append("\t\t\t\t$.post($(\"#"+formId+"\",window.top.document).attr('action'),$(\"#"+formId+"\",window.top.document).serialize(),function(e){\n");
        sb.append("\t\t\t\t\tif(e.result){\n");
        sb.append("\t\t\t\t\t\t$('.modal-dialog', window.top.document).parent('div').remove()\n");
        sb.append("\t\t\t\t\t\t$('body', window.top.document).find('.modal-backdrop').remove();\n");
        sb.append("\t\t\t\t\t\t$(window.parent.document).contents().find(\".tab-pane.fade.active.in iframe\")[0].contentWindow.doQuery();\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.alert({message:'增加数据成功!'});\n");
        sb.append("\t\t\t\t\t}else{\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.alert({message:'增加数据失败!'});\n");
        sb.append("\t\t\t\t\t}\n");
        sb.append("\t\t\t\t })\n");
        sb.append("\t\t\t}\n");
        sb.append("\t\t });\n");
        sb.append("\t})\n");
        sb.append("\t]]>\n");
        sb.append("</script>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

    /**
     * 功能描述：生成LIST的HTML页面模板
     * @param author 创建者
     * @param table 表实体
     * @param beanName 实体名字
     */
    public static String genListHtml(String author, TableModel table,String beanName){
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
        sb.append("\t<div class=\"panel panel-default\">\n");
        sb.append("\t\t<div class=\"panel-heading\">查询条件</div>\n");
        sb.append("\t\t<div class=\"panel-body\">\n");
        sb.append("\t\t\t<form class=\"form-inline\" role=\"form\" style=\"float: left; width: 100%;margin-left: 20px;\" method=\"post\" id=\""+queryFormId+"\">\n");
        // 遍历循环整个表的实体
        for (ColumnModel columnModel : columnModelList) {
            if(!columnModel.isPrimaryKey()|| !columnModel.isAutoIncrement()){
                sb.append("\t\t\t\t<div class=\"form-group\">\n");
                // 获取字段类型
                type = columnModel.getFieldName();
                // 获取表字段的备注
                if(StringUtils.isNotBlank(columnModel.getRemarks())){
                    remark = columnModel.getRemarks();
                }else{
                    remark = type;
                }
                sb.append("\t\t\t\t\t<label for=\""+type+"\">"+remark+":</label>\n");
                sb.append("\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\""+type+"\" id=\""+type+"\"  placeholder=\"请输入"+remark+"\" />\n");
                sb.append("\t\t\t\t</div>\n");
            }
        }
        sb.append("\t\t\t\t<div class=\"form-group\">\n");
        sb.append("\t\t\t\t\t<button type=\"button\" id=\"queryBtn\" onclick=\"doQuery();\" class=\"btn btn-primary\">查询</button>\n");
        sb.append("\t\t\t\t</div>\n");
        sb.append("\t\t\t</form>\n");
        sb.append("\t\t</div>\n");
        sb.append("\t\t</div>\n");
        sb.append("\t</div>\n");
        sb.append("\t<table id=\"" + tableName + "\" style=\"margin-top: -50px;\">\n");
        sb.append("\t</table>\n");
        sb.append("</div>\n");
        // JavaScript执行脚本创建的开始
        sb.append("<script th:inline=\"javascript\">\n");
        sb.append("\t$(function() {\n");
        sb.append("\t\tinitTable();\n");
        //sb.append("$('#"+tableName+"').bootstrapTable('hideColumn', 'id');");
        sb.append("\t});\n");
        sb.append("\tfunction doQuery(){\n");
        sb.append(" \t\t$('#"+tableName+"').bootstrapTable('refresh');    //刷新表格\n");
        sb.append("\t}\n");
        sb.append("\tfunction initTable(){\n");
        sb.append("\t\t$('#"+tableName+"').bootstrapTable({\n");
        sb.append("\t\t\turl:\""+beanName+"/list\",\n");
        sb.append("\t\t\theight: $(window.parent.document).find(\"#wrapper\").height() - 252,\n");
        sb.append("\t\t\twidth:$(window).width(),\n");
        sb.append("\t\t\tshowColumns:true,\n");
        sb.append("\t\t\tformId :\""+queryFormId+"\",\n");
        sb.append("\t\t\tpagination : true,\n");
        sb.append("\t\t\tsortName : 'id',\n");
        sb.append("\t\t\tsortOrder : 'desc',\n");
        sb.append("\t\t\tclickToSelect: true,// 单击某一行的时候选中某一条记录\n");
        sb.append("\t\t\tpageSize : 13,\n");
        sb.append("\t\t\ttoolbars:[\n");
        sb.append("\t\t\t\t{\n");
        sb.append("\t\t\t\t\ttext: '添加',\n");
        sb.append("\t\t\t\t\ticonCls: 'glyphicon glyphicon-plus',\n");
        sb.append("\t\t\t\t\thandler: function () {\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.dialog({title:\"添加\",url:\""+beanName+"/addPage\",width:400,height:500})\n");
        sb.append("\t\t\t\t}\n");
        sb.append("\t\t\t\t},\n");
        sb.append("\t\t\t\t{\n");
        sb.append("\t\t\t\t\ttext: '修改',\n");
        sb.append("\t\t\t\t\ticonCls: 'glyphicon glyphicon-pencil',\n");
        sb.append("\t\t\t\t\thandler: function () {\n");
        sb.append("\t\t\t\t\t\tvar rows = $('#"+tableName+"').bootstrapTable('getSelections');\n");
        sb.append("\t\t\t\t\t\tif(rows.length==0||rows.length>1){\n");
        sb.append("\t\t\t\t\t\t\twindow.Ewin.alert({message:'请选择一条需要修改的数据!'});\n");
        sb.append("\t\t\t\t\t\t\treturn false;\n");
        sb.append("\t\t\t\t\t\t}\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.dialog({title:\"修改\",url:\""+beanName+"/updatePage?id=\"+rows[0].id,width:400,height:500});\n");
        sb.append("\t\t\t\t}\n");
        sb.append("\t\t\t\t},\n");
        sb.append("\t\t\t\t{\n");
        sb.append("\t\t\t\t\ttext: '删除',\n");
        sb.append("\t\t\t\t\ticonCls: 'glyphicon glyphicon-remove',\n");
        sb.append("\t\t\t\t\thandler: function () {\n");
        sb.append("\t\t\t\t\t\tvar rows = $('#"+tableName+"').bootstrapTable('getSelections');\n");
        sb.append("\t\t\t\t\t\tif(rows.length==0){\n");
        sb.append("\t\t\t\t\t\t\twindow.Ewin.alert({message:'请选择一条需要删除的数据!'});\n");
        sb.append("\t\t\t\t\t\t\treturn false;\n");
        sb.append("\t\t\t\t\t\t}\n");
        sb.append("\t\t\t\t\t\twindow.Ewin.confirm({title:'提示',message:'是否要删除您所选择的记录？',width:500}).on(function (e) {\n");
        sb.append("\t\t\t\t\t\tif (e) {\n");
        sb.append("\t\t\t\t\t\t\t$.post(\""+beanName+"/removeBath\",{json:JSON.stringify(rows)},function(e){\n");
        sb.append("\t\t\t\t\t\t\t\tif(e.result){\n");
        sb.append("\t\t\t\t\t\t\t\t\twindow.Ewin.alert({message:e.msg});\n");
        sb.append("\t\t\t\t\t\t\t\t\tdoQuery();\n");
        sb.append("\t\t\t\t\t\t\t\t}\n");
        sb.append("\t\t\t\t\t\t\t});\n");
        sb.append("\t\t\t\t\t\t\t}\n");
        sb.append("\t\t\t\t\t\t});\n");
        sb.append("\t\t\t\t\t}\n");
        sb.append("\t\t\t\t}\n");
        sb.append("\t\t\t],\n");
        sb.append("\t\t\tcolumns: [\n");
        sb.append("\t\t\t\t{\n");
        sb.append("\t\t\t\t\tcheckbox: true\n");
        sb.append("\t\t\t\t},\n");
        sb.append("\t\t\t\t{\n");
        sb.append("\t\t\t\t\tfield: '',\n");
        sb.append("\t\t\t\t\ttitle: '序号',\n");
        sb.append("\t\t\t\t\tformatter: function (value, row, index) {\n");
        sb.append("\t\t\t\t\t\treturn index+1;\n");
        sb.append("\t\t\t\t\t}\n");
        sb.append("\t\t\t\t},\n");
        // 遍历循环整个表的实体
        for (int i=0;i<columnModelList.size();i++) {
            ColumnModel columnModel = columnModelList.get(i);
            if(!columnModel.isPrimaryKey()|| !columnModel.isAutoIncrement()){
                // 获取字段类型
                type = columnModel.getFieldName();
                // 获取表字段的备注
                if(StringUtils.isNotBlank(columnModel.getRemarks())){
                    remark = columnModel.getRemarks();
                }else{
                    remark = type;
                }
                sb.append("\t\t\t\t{\n");
                sb.append("\t\t\t\t\tfield : '"+type+"',\n");
                sb.append("\t\t\t\t\ttitle : '"+remark+"',\n");
                sb.append("\t\t\t\t\talign : 'center',\n");
                sb.append("\t\t\t\t\tvalign : 'middle',\n");
                sb.append("\t\t\t\t\tsortable : true\n");
                if(i==columnModelList.size()){
                    sb.append("\t\t\t\t}\n");
                }else{
                    sb.append("\t\t\t\t},\n");
                }
            }
        }
        sb.append("\t\t\t]\n");
        sb.append("\t\t\t});\n");
        sb.append("\t\t}\n");
        sb.append("\t</script>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

}
