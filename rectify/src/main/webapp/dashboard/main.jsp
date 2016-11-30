<%-- 
    Document   : main
    Created on : Nov 28, 2016, 11:42:12 AM
    Author     : david
--%>

<%@page import="pt.inesc.rectify.Rectify"%>
<%@page import="pt.inesc.rectify.RectifyConstants"%>
<%@page import="pt.inesc.rectify.RectifyUtils"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">HTTP Proxy</h3></div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        
                        <div class="form-group">
                        <div class="col-sm-4"><span class="control-label">Proxy state:</span></div>
                        <div class="col-sm-8">
                                <input type="checkbox" data-toggle="toggle" data-on="Enabled" <%= Rectify.httpProxy != null? "checked": "" %> data-off="Disabled" id="http_proxy_running" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4"><span class="control-label">HTTP Server Address</span></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control"  id="httpremotehost" <%= Rectify.httpProxy != null? "disabled": "" %> value="<%= RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_REMOTE_HOST) %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4"><span class="control-label" >HTTP Server Port</span></div>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="httpremoteport" <%= Rectify.httpProxy != null? "disabled": "" %> value="<%= RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_REMOTE_PORT) %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4"><span class="control-label" >Proxy Port</span></div>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="httplocalport" <%= Rectify.httpProxy != null? "disabled": "" %> value="<%= RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_LOCAL_PORT) %>"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">HTTP Log</h3></div>
                <div class="panel-body">
                    <div>
                        <div class="form-group pull-right">
                            <input type="text" class="search form-control" placeholder="Search request..." />
                        </div><span class="counter pull-right"></span>
                        <table class="table table-hover table-bordered results">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th class="col-md-5 col-xs-4">Endpoint</th>
                                    <th class="col-md-4 col-xs-3">Host</th>
                                    <th class="col-md-3 col-xs-5">TimeStamp</th>
                                </tr>
                                <tr class="warning no-result">
                                    <td colspan="4"><i class="fa fa-warning"></i>No result</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>www.ist.pt</td>
                                    <td>192.168.2.4</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>www.ist.pt</td>
                                    <td>192.168.2.4</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                                <tr>
                                    <th scope="row">3</th>
                                    <td>www.ist.pt</td>
                                    <td>192.168.2.4</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                                <tr>
                                    <th scope="row">4</th>
                                    <td>www.ist.pt</td>
                                    <td>192.168.2.4</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">DB Proxy</h3></div>
                <div class="panel-body">
                    <form class="form-horizontal">
                                                <div class="form-group">
                        <div class="col-sm-4"><span class="control-label">Proxy state:</span></div>
                        <div class="col-sm-8">
                                <input type="checkbox" data-toggle="toggle" data-on="Enabled" data-off="Disabled" <%= Rectify.dbProxy != null? "checked": "" %> id="db_proxy_running" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4"><span class="control-label">DB Server Address</span></div>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="dbremotehost" <%= Rectify.dbProxy != null? "disabled": "" %> value="<%= RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_DB_REMOTE_HOST) %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4"><span class="control-label" >DB Server Port</span></div>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="dbremoteport" <%= Rectify.dbProxy != null? "disabled": "" %> value="<%= RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_REMOTE_PORT) %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4"><span class="control-label" >Proxy Port</span></div>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="dblocalport" <%= Rectify.dbProxy != null? "disabled": "" %> value="<%= RectifyUtils.getConfigurationEntry(RectifyConstants.CFG_HTTP_LOCAL_PORT) %>"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">DB Log</h3></div>
                <div class="panel-body">
                    <div>
                        <div class="form-group pull-right">
                            <input type="text" class="search form-control" placeholder="Search query..." />
                        </div><span class="counter pull-right"></span>
                        <table class="table table-hover table-bordered results">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th class="col-md-5 col-xs-4">Query Type</th>
                                    <th class="col-md-4 col-xs-3">Affected Table</th>
                                    <th class="col-md-3 col-xs-5">TimeStamp</th>
                                </tr>
                                <tr class="warning no-result">
                                    <td colspan="4"><i class="fa fa-warning"></i>No result</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>INSERT</td>
                                    <td>users</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>INSERT</td>
                                    <td>users</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                                <tr>
                                    <th scope="row">3</th>
                                    <td>INSERT</td>
                                    <td>users</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                                <tr>
                                    <th scope="row">4</th>
                                    <td>UPDATE</td>
                                    <td>users</td>
                                    <td>10/2/2015 15:35:35.0</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Knowledge Base</h3></div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group"><span class="label-primary">## Registered requests in the Knowledge Base</span></div>
                        <div class="form-group">
                            <div>Trainning Mode:
                                <input type="checkbox" data-toggle="toggle" data-on="ON" data-off="OFF" id="trainning_mode" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        
        
  $(function() {
    $('#http_proxy_running').change(function() {
      if ($(this).prop('checked')){
          //start http proxy
          var httpRemoteHost = $('#httpremotehost').val();
          var httpRemotePort = $('#httpremoteport').val();
          var httpLocalPort = $('#httplocalport').val();
          $(location).attr('href', 'actions/start_http_proxy.jsp?return=/rectify&httpremotehost='+httpRemoteHost + '&httpremoteport='+httpRemotePort+'&httplocalport='+httpLocalPort);
      }else{
          //stopt http proxy
          $(location).attr('href', 'actions/stop_http_proxy.jsp?return=/rectify');
      }
    })
    
    $('#db_proxy_running').change(function() {
      if ($(this).prop('checked')){
          //start db proxy
          var dbRemoteHost = $('#dbremotehost').val();
          var dbRemotePort = $('#dbremoteport').val();
          var dbLocalPort = $('#dblocalport').val();
          $(location).attr('href', 'actions/start_db_proxy.jsp?return=/rectify&dbremotehost='+dbRemoteHost + '&dbremoteport='+dbRemotePort+'&dblocalport='+dbLocalPort);
      }else{
          //stopt db proxy
          $(location).attr('href', 'actions/stop_db_proxy.jsp?return=/rectify');
      }
    })
    
    
  })
  
  
  
</script>