SuperMap.IS.LayerControl = function(container, map, param) {
    var _map = null;
    var _layers = null;
    var _layerItems = null;
    var _checkedAllQueryable = false;
    var _checkedAllVisible = false;
    var _id = container.id;
    var _inited = false;
    var _imageFormat = 1;
    var _isNeedTitle = false;
    if (param && typeof(param.isNeedTitle) != "undefined") {
        _isNeedTitle = param.isNeedTitle
    }
    var _isShowInCurrentPage = true;
    if (param && typeof(param.isShowInCurrentPage) != "undefined") {
        _isShowInCurrentPage = param.isShowInCurrentPage
    }
    var _eventsList = new Array();
    var _eventsNameList = new Array();
    var _curLayerContextMenu;
    var _lastMapName;
    var _indexs;
    var _filterResult;
    var _headerBackColor = "White";
    if (param && typeof(param.headerBackColor) != "undefined") {
        _headerBackColor = param.headerBackColor
    }
    this.headerFont = new SuperMap.IS.Font();
    if (param && typeof(param.headerFont) != "undefined") {
        var headerFont = _eval("(" + param.headerFont + ")");
        this.headerFont.FromJSON(headerFont);
        headerFont = null
    }
    var _headerForeColor = "Black";
    if (param && typeof(param.headerForeColor) != "undefined") {
        _headerForeColor = param.headerForeColor
    }
    var _itemBackColor = "White";
    if (param && typeof(param.itemBackColor) != "undefined") {
        _itemBackColor = param.itemBackColor
    }
    this.itemFont = new SuperMap.IS.Font();
    if (param && typeof(param.itemFont) != "undefined") {
        var itemFont = _eval("(" + param.itemFont + ")");
        this.itemFont.FromJSON(itemFont);
        itemFont = null
    }
    var _itemForeColor = "Black";
    if (param && typeof(param.itemForeColor) != "undefined") {
        _itemForeColor = param.itemForeColor
    }
    this.layerNameText = "LayerName";
    if (param && typeof(param.layerNameText) != "undefined") {
        this.layerNameText = param.layerNameText
    }
    this.visibleText = "Visible";
    if (param && typeof(param.visibleText) != "undefined") {
        this.visibleText = param.visibleText
    }
    this.queryableText = "Queryable";
    if (param && typeof(param.queryableText) != "undefined") {
        this.queryableText = param.queryableText
    }
    this.submitButtonText = "Refresh";
    if (param && typeof(param.submitButtonText) != "undefined") {
        this.submitButtonText = param.submitButtonText
    }
    this.resetButtonText = "Reset";
    if (param && typeof(param.resetButtonText) != "undefined") {
        this.resetButtonText = param.resetButtonText
    }
    this.buttonCssClass = "";
    if (param && typeof(param.buttonCssClass) != "undefined") {
        this.buttonCssClass = param.buttonCssClass
    }
    this.checkBoxCssClass = "";
    if (param && typeof(param.checkBoxCssClass) != "undefined") {
        this.checkBoxCssClass = param.checkBoxCssClass
    }
    this.separator = "@";
    if (param && typeof(param.separator) != "undefined") {
        this.separator = param.separator
    }
    this.overFlowEnabled = true;
    if (param && typeof(param.overFlowEnabled) != "undefined") {
        this.overFlowEnabled = param.overFlowEnabled
    }
    this.quickSubmitEnabled = false;
    if (param && typeof(param.quickSubmitEnabled) != "undefined") {
        this.quickSubmitEnabled = param.quickSubmitEnabled
    }
    this.visibleEnabled = true;
    if (param && typeof(param.visibleEnabled) != "undefined") {
        this.visibleEnabled = param.visibleEnabled
    }
    this.queryableEnabled = true;
    if (param && typeof(param.queryableEnabled) != "undefined") {
        this.queryableEnabled = param.queryableEnabled
    }
    this.expand = false;
    if (param && typeof(param.expand) != "undefined") {
        this.expand = param.expand
    }
    var _isDescendServer = false;
    if (param && typeof(param.isDescendServer) != "undefined") {
        _isDescendServer = param.isDescendServer
    }
    var _sortType = "caption";
    if (param && typeof(param.sortType) != "undefined") {
        _sortType = param.sortType
    }
    var _resourceWidth = 16;
    if (param && param.resourceWidth) {
        _resourceWidth = param.resourceWidth
    }
    var _resourceHeight = 16;
    if (param && param.resourceHeight) {
        _resourceHeight = param.resourceHeight
    }
    var _themeNameEnabled = true;
    if (param && param.themeNameEnabled) {
        _themeNameEnabled = param.themeNameEnabled
    }
    this.layerFilter = null;
    var _self = this;
    var _containerInside = null;
    var _lastLayers = null;
    this.Destroy = _Destroy;
    this.SetLayerContextMenu = _SetLayerContextMenu;
    this.SaveLayerStatus = _SaveLayerStatus;
    this.SortLayerItem = _SortLayerItem;
    this.Update = _Update;
    this.AttachEvent = _AttachEvent;
    this.DetachEvent = _DetachEvent;
    if (map) {
        map.AttachEvent("oninit", _Init);
        map.AttachEvent("ondestroying", _Destroy)
    }
    function _Init() {
        if (_inited) {
            return
        }
        if (_containerInside) {
            if (_containerInside.parentNode) {
                _containerInside.parentNode.removeChild(_containerInside)
            }
            _containerInside = null
        }
        _containerInside = document.createElement("div");
        var heightUnit = "px";
        var widthUnit = "px";
        var width = container.style.pixelWidth;
        if (param && typeof(param.width) != "undefined") {
            widthUnit = _GetUnit(param.width);
            width = param.width.toString().replace(widthUnit, "")
        }
        var height = container.style.pixelHeight;
        if (param && typeof(param.height) != "undefined") {
            heightUnit = _GetUnit(param.height);
            height = param.height.toString().replace(heightUnit, "")
        }
        _containerInside.style.width = width + widthUnit;
        _containerInside.style.height = height + heightUnit;
        _containerInside.style.position = "relative";
        container.appendChild(_containerInside);
        if (_self.overFlowEnabled) {
            _containerInside.style.overflow = "auto";
            _containerInside.style.overflowX = "auto";
            _containerInside.style.overflowY = "auto"
        }
        _inited = true;
        _map = map;
        _layers = map.layers;
        _InitIndexs();
        _InitLayerFilter();
        _InitLayerItems();
        _RenderLayers();
        _InitContext();
        _lastMapName = map.mapName;
        if (map) {
            map.AttachEvent("onendswitchmap", _Update);
            map.AttachEvent("onchangelayer", _Update)
        }
    }
    function _GetUnit(value) {
        if (value.toString().indexOf("px") != -1) {
            return "px"
        } else {
            if (value.toString().indexOf("%") != -1) {
                return "%"
            }
        }
        return "px"
    }
    function _Destroy() {
        if (_map) {
            _map.DetachEvent("onendswitchmap", _Update);
            _map.DetachEvent("onchangelayer", _Update);
            map.DetachEvent("oninit", _Init);
            map.DetachEvent("ondestroying", _Destroy)
        }
        _layers = null;
        _id = null;
        _inited = null;
        _imageFormat = null;
        _isNeedTitle = null;
        _isShowInCurrentPage = null;
        _eventsList = null;
        _eventsNameList = null;
        _curLayerContextMenu = null;
        _lastMapName = null;
        _indexs = null;
        _filterResult = null;
        _headerBackColor = null;
        _self.headerFont.Destroy();
        _self.headerFont = null;
        _headerForeColor = null;
        _itemBackColor = null;
        _self.itemFont.Destroy();
        _self.itemFont = null;
        _itemForeColor = null;
        if (container) {
            container.innerHTML = ""
        }
        _self = null;
        container = null
    }
    function _InitContext() {
        var hidden = document.getElementById(container.id + "_hiddenLayerContextMenu");
        if (hidden && hidden.value) {
            var layerContextMenuJSON = hidden.value;
            _curLayerContextMenu = _JSONToAction(layerContextMenuJSON);
            _SetLayerContextMenu(_curLayerContextMenu)
        }
    }
    function _InitLayerFilter() {
        var hidden = document.getElementById(container.id + "_hiddenFilter");
        if (hidden) {
            _filterResult = hidden.value.split(",")
        }
    }
    function _InitLayerItems() {
        var hidden = document.getElementById(container.id + "_hiddenLayerItems");
        if (hidden && hidden.value) {
            _layerItems = _eval(hidden.value)
        } else {
            _ResetLayerItems(map.layers)
        }
    }
    function _InitIndexs() {
        _indexs = new Array();
        for (var i = 0; i < map.layers.length; i++) {
            _indexs[i] = i
        }
    }
    function _ResetLayerItems(layers) {
        if (!layers) {
            return
        }
        if (_layerItems) {
            _layerItems = null
        }
        _layerItems = new Array();
        for (var i = 0; i < layers.length; i++) {
            _layerItems[i] = new SuperMap.IS.LayerItem();
            _layerItems[i].caption = layers[i].caption;
            _layerItems[i].visibleChecked = layers[i].visible;
            _layerItems[i].queryableChecked = layers[i].queryable;
            _layerItems[i].value = i
        }
        _SetLayerItemsHidden()
    }
    function _RenderLayers() {
        var url;
        var innerHTML = "";
        innerHTML += "<table>";
        if (_isNeedTitle) {
            innerHTML += "<tr><td ColSpan=4 align=center valign=middle>" + SuperMap.IS.LayerControlResource.title + " (" + map.mapName + ")</td></tr>"
        }
        if (typeof(_isDescendServer) == "boolean") {
            _SortLayers(_sortType, _isDescendServer);
            _isDescendServer = null
        }
        var trString = "";
        var tdString = "";
        trString += "<tr style='background-color:" + _headerBackColor + ";color:" + _headerForeColor + ";font-family:" + _self.headerFont.fontFamily.name + ";font-size:" + _self.headerFont.size + ";";
        if (_self.headerFont.bold) {
            trString += "font-weight:bold;"
        }
        if (_self.headerFont.italic) {
            trString += "font-style:itlic;"
        }
        if (_self.headerFont.underline && _self.headerFont.strikeout) {
            trString += "text-decoration:underline line-through;"
        } else {
            if (_self.headerFont.underline) {
                trString += "text-decoration:underline;"
            } else {
                if (_self.headerFont.strikeout) {
                    trString += "text-decoration:line-through;"
                }
            }
        }
        trString += "'>";
        tdString = "<td ColSpan=2>" + _self.layerNameText + "</td>";
        if (_self.visibleEnabled) {
            tdString += "<td>" + _self.visibleText + "</td>"
        }
        if (_self.queryableEnabled) {
            tdString += "<td>" + _self.queryableText + "</td>"
        }
        trString += tdString + "</tr>";
        innerHTML += trString;
        var resourcesParam = new SuperMap.IS.ResourceParam();
        resourcesParam.width = _resourceWidth;
        resourcesParam.height = _resourceHeight;
        for (var i = 0; i < _indexs.length; i++) {
            var m = _indexs[i];
            if (_layerItems[m].renderStyle == 2) {
                continue
            }
            if (!_layers[m]) {
                continue
            }
            if (_filterResult && _filterResult[m] == "false") {
                continue
            }
            if (_self.layerFilter && !_self.layerFilter(_layers[m])) {
                continue
            }
            var innerHTMLForEachLayer = "";
            var resourceType = "";
            switch (_layers[m].type) {
            case SuperMap.IS.LayerType.point:
                resourceType = 0;
                break;
            case SuperMap.IS.LayerType.line:
                resourceType = 1;
                break;
            case SuperMap.IS.LayerType.polygon:
                resourceType = 2;
                break;
            case SuperMap.IS.LayerType.grid:
                resourceType = 2;
                break;
            default:
                break
            }
            var themeImgID = _layers[m].name + "_StyleIMG";
            resourcesParam.style = _layers[m].style;
            resourcesParam.resourceType = resourceType;
            resourcesParam.imageFormat = _imageFormat;
            innerHTMLForEachLayer = "<tr style='background-color:" + _itemBackColor + ";color:" + _itemForeColor + ";font-family:" + _self.itemFont.fontFamily.name + ";font-size:" + _self.itemFont.size + ";";
            if (_self.itemFont.bold) {
                innerHTMLForEachLayer += "font-weight:bold;"
            }
            if (_self.itemFont.italic) {
                innerHTMLForEachLayer += "font-style:itlic;"
            }
            if (_self.itemFont.underline && _self.itemFont.strikeout) {
                innerHTMLForEachLayer += "text-decoration:underline line-through;"
            } else {
                if (_self.itemFont.underline) {
                    innerHTMLForEachLayer += "text-decoration:underline;"
                } else {
                    if (_self.itemFont.strikeout) {
                        innerHTMLForEachLayer += "text-decoration:line-through;"
                    }
                }
            }
            innerHTMLForEachLayer += "'>";
            var bHasTheme = false;
            if (_layers[m].themeUnique || _layers[m].themeRange || _layers[m].themeGraph || _layers[m].themeGraduatedSymbol || _layers[m].themeLabel || _layers[m].themeDotDensity || _layers[m].themeGridRange || _layers[m].themeCustom) {
                bHasTheme = true
            }
            innerHTMLForEachLayer += '<td valign="top" width="20px">';
            if (bHasTheme) {
                if (_self.expand) {
                    innerHTMLForEachLayer += '<img id="Switch_' + _id + "_" + _layers[m].name + '_Themes" src="images/expand.gif"'
                } else {
                    innerHTMLForEachLayer += '<img id="Switch_' + _id + "_" + _layers[m].name + '_Themes" src="images/collapse.gif"'
                }
            }
            innerHTMLForEachLayer += "</td><td>";
            if (_layers[m].type == SuperMap.IS.LayerType.point || _layers[m].type == SuperMap.IS.LayerType.line || _layers[m].type == SuperMap.IS.LayerType.polygon) {
                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="' + map.GenerateResourceRequest(resourcesParam) + '" >'
            } else {
                if (_layers[m].type == SuperMap.IS.LayerType.network) {
                    innerHTMLForEachLayer += '<img id="' + themeImgID + '" width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/Network.gif" >'
                } else {
                    if (_layers[m].type == SuperMap.IS.LayerType.text) {
                        innerHTMLForEachLayer += '<img id="' + themeImgID + '" width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/Text.gif" >'
                    } else {
                        if (_layers[m].type == SuperMap.IS.LayerType.image) {
                            innerHTMLForEachLayer += '<img id="' + themeImgID + '" width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/Image.gif" >'
                        } else {
                            if (_layers[m].type == SuperMap.IS.LayerType.mrSID) {
                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/MRSID.gif" >'
                            } else {
                                if (_layers[m].type == SuperMap.IS.LayerType.grid) {
                                    innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/GRID.gif" >'
                                } else {
                                    if (_layers[m].type == SuperMap.IS.LayerType.dem) {
                                        innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/Dem.gif" >'
                                    } else {
                                        if (_layers[m].type == SuperMap.IS.LayerType.ecw) {
                                            innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/Ecw.gif" >'
                                        } else {
                                            if (_layers[m].type == SuperMap.IS.LayerType.cad) {
                                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/CAD.gif" >'
                                            } else {
                                                innerHTMLForEachLayer += '<img id="' + themeImgID + '"  width=' + _resourceWidth + " height=" + _resourceHeight + ' src="images/spacer.gif" >'
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            innerHTMLForEachLayer += '<span style="font-weight:bold" oncontextmenu =\'' + _id + ".OnContextMenu(event," + m + ");return false;'>" + _layerItems[m].caption.split(_self.separator)[0] + "</span>";
            if (_self.expand) {
                innerHTMLForEachLayer += '<table id="' + _id + "_" + _layers[m].name + '_Themes" style="display:block">'
            } else {
                innerHTMLForEachLayer += '<table id="' + _id + "_" + _layers[m].name + '_Themes" style="display:none">'
            }
            var uniqueTheme = _layers[m].themeUnique;
            if (uniqueTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Unique", uniqueTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var rangeTheme = _layers[m].themeRange;
            if (rangeTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Range", rangeTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var graphTheme = _layers[m].themeGraph;
            if (graphTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Graph", graphTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var graduatedSymbolTheme = _layers[m].themeGraduatedSymbol;
            if (graduatedSymbolTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "GraduatedSymbol", graduatedSymbolTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var labelTheme = _layers[m].themeLabel;
            if (labelTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Label", labelTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var dotDemsityTheme = _layers[m].themeDotDensity;
            if (dotDemsityTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "DotDensity", dotDemsityTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var gridRangeTheme = _layers[m].themeGridRange;
            if (gridRangeTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "GridRange", gridRangeTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            var customTheme = _layers[m].themeCustom;
            if (customTheme != null) {
                innerHTMLForEachLayer += WriteStartScriptPart(m, "Custom", customTheme, resourceType);
                innerHTMLForEachLayer += WriteEndScriptPart()
            }
            innerHTMLForEachLayer += "</table></td>";
            if (_self.visibleEnabled) {
                innerHTMLForEachLayer += "<td valign=top><input type='checkbox' id='" + container.id + "_" + _layers[m].name + ":V'";
                if (_self.checkBoxCssClass) {
                    innerHTMLForEachLayer += "class='" + _self.checkBoxCssClass + "'"
                }
                if (_layerItems[m].renderStyle = 1) {
                    innerHTMLForEachLayer += " readonly=true "
                }
                if (_layerItems[m].visibleChecked) {
                    innerHTMLForEachLayer += "checked='checked'  /></td>"
                } else {
                    innerHTMLForEachLayer += " /></td>"
                }
            }
            if (_self.queryableEnabled) {
                innerHTMLForEachLayer += "<td valign=top><input type='checkbox' id='" + container.id + "_" + _layers[m].name + ":Q'";
                if (_self.checkBoxCssClass) {
                    innerHTMLForEachLayer += "class='" + _self.checkBoxCssClass + "'"
                }
                if (_layerItems[m].renderStyle = 1) {
                    innerHTMLForEachLayer += " readonly=true "
                }
                if (_layerItems[m].queryableChecked) {
                    innerHTMLForEachLayer += "checked='checked'  /></td>"
                } else {
                    innerHTMLForEachLayer += " /></td>"
                }
            }
            innerHTMLForEachLayer += "</tr>";
            innerHTML += innerHTMLForEachLayer
        }
        trString = "<tr>";
        tdString = "<td ColSpan=2><input type='button'  id='save' value='" + _self.submitButtonText + "'";
        if (_self.buttonCssClass) {
            tdString += "class='" + _self.buttonCssClass + "'"
        }
        tdString += "/>";
        tdString += "<input type='reset' id='reset' value='" + _self.resetButtonText + "'";
        if (_self.buttonCssClass) {
            tdString += "class='" + _self.buttonCssClass + "'"
        }
        tdString += " /></td>";
        if (_self.visibleEnabled) {
            tdString += "<td><input type='checkbox' id='" + container.id + "_CheckVisibleAll'";
            if (_self.checkBoxCssClass) {
                tdString += "class='" + _self.checkBoxCssClass + "'"
            }
            if (_checkedAllVisible) {
                tdString += " checked "
            }
            tdString += "/></td>"
        }
        if (_self.queryableEnabled) {
            tdString += "<td><input type='checkbox' id='" + container.id + "_CheckQueryableAll'";
            if (_self.checkBoxCssClass) {
                tdString += "class='" + _self.checkBoxCssClass + "'"
            }
            if (_checkedAllQueryable) {
                tdString += " checked "
            }
            tdString += " /></td>"
        }
        trString += tdString + "</tr>";
        innerHTML += trString;
        innerHTML += "</table>";
        var form = document.createElement("form");
        form.id = "layersForm";
        form.innerHTML = innerHTML;
        var layersForm = document.getElementById("layersForm");
        if (_containerInside.contains(layersForm)) {
            _containerInside.removeChild(layersForm)
        }
        _containerInside.appendChild(form);
        _JudgeMethod("save", _SaveLayerStatus);
        _JudgeMethod(container.id + "_CheckVisibleAll", _CheckedAllVisible);
        _JudgeMethod(container.id + "_CheckQueryableAll", _CheckedAllQueryable);
        for (var i = 0; i < _indexs.length; i++) {
            var m = _indexs[i];
            var bHasTheme = false;
            if (_layerItems[m].renderStyle == 2) {
                continue
            }
            if (!_layers[m]) {
                continue
            }
            if (_filterResult && _filterResult[m] == "false") {
                continue
            }
            if (_self.layerFilter && !_self.layerFilter(_layers[m])) {
                continue
            }
            if (_layers[m].themeUnique || _layers[m].themeRange || _layers[m].themeGraph || _layers[m].themeGraduatedSymbol || _layers[m].themeLabel || _layers[m].themeDotDensity || _layers[m].themeGridRange || _layers[m].themeCustom) {
                bHasTheme = true
            }
            if (bHasTheme) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Themes", 
                function(e) {
                    _SwitchThemesDisplay(e)
                })
            }
            var uniqueTheme = _layers[m].themeUnique;
            if (uniqueTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Unique", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Unique", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var rangeTheme = _layers[m].themeRange;
            if (rangeTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Range", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Range", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var graphTheme = _layers[m].themeGraph;
            if (graphTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_Graph", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Graph", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var graduatedSymbolTheme = _layers[m].themeGraduatedSymbol;
            if (graduatedSymbolTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_GraduatedSymbol", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_GraduatedSymbol", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var labelTheme = _layers[m].themeLabel;
            if (labelTheme != null) {
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Label", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var dotDemsityTheme = _layers[m].themeDotDensity;
            if (dotDemsityTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_DotDensity", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_DotDensity", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var gridRangeTheme = _layers[m].themeGridRange;
            if (gridRangeTheme != null) {
                _JudgeMethod("Switch_" + _id + "_" + _layers[m].name + "_GridRange", 
                function(e) {
                    _SwitchThemesDisplay(e)
                });
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_GridRange", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            var customTheme = _layers[m].themeCustom;
            if (customTheme != null) {
                if (_self.quickSubmitEnabled) {
                    _JudgeMethod(_id + "_" + m + "_Custom", 
                    function(e) {
                        _SwitchThemesVisible(e)
                    })
                }
            }
            if (_self.visibleEnabled && _self.quickSubmitEnabled) {
                _JudgeMethod(container.id + "_" + _layers[m].name + ":V", _SaveLayerStatus)
            }
            if (_self.queryableEnabled && _self.quickSubmitEnabled) {
                _JudgeMethod(container.id + "_" + _layers[m].name + ":Q", _SaveLayerStatus)
            }
        }
        _lastLayers = new Array();
        for (var i = 0; i < _layers.length; i++) {
            _lastLayers[i] = new SuperMap.IS.Layer();
            _lastLayers[i].FromJSON(_layers[i])
        }
    }
    function _JudgeMethod(id, method) {
        var inputbox = document.getElementById(id);
        if (inputbox != null) {
            if (_ygPos.browser == "ie") {
                inputbox.attachEvent("onclick", method)
            } else {
                inputbox.addEventListener("click", method, true)
            }
            inputbox = null
        }
    }
    function _AttachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            events = new Array();
            _eventsList[event] = events;
            _eventsNameList.push(event)
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                return true
            }
        }
        events.push(listener)
    }
    function _DetachEvent(event, listener) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        for (var i = 0; i < events.length; i++) {
            if (events[i] == listener) {
                events.splice(i, 1)
            }
        }
    }
    function _TriggerEvent(event, arguments, userContext) {
        var events = _eventsList[event];
        if (!events) {
            return
        }
        if (!arguments) {
            arguments = _GenerateEventArg()
        }
        var eventsTemp = events.concat();
        for (var i = 0; i < eventsTemp.length; i++) {
            if (eventsTemp[i]) {
                eventsTemp[i](arguments, userContext)
            }
        }
        while (eventsTemp.length > 0) {
            eventsTemp.pop()
        }
    }
    function _GenerateEventArg() {
        var arguments = _map.layers;
        return arguments
    }
    this.TriggerServerEvent = function(eventName, e) {
        _eval(container.id + "_doPostBack(container.id, eventName)")
    };
    function WriteStartScriptPart(index, themeType, theme, resourceType) {
        var str = '<tr><td valign="top" width="20px">';
        if (themeType != "Label" && themeType != "Custom") {
            str += '<img id="Switch_' + _id + "_" + _layers[index].name + "_" + themeType + '"  src="images/expand.gif">'
        }
        var themeName = "";
        if (theme != null) {
            themeName = theme.caption
        }
        var picName = "";
        var themeInnerHTML = "";
        var resourcesParam = new SuperMap.IS.ResourceParam();
        resourcesParam.width = _resourceWidth;
        resourcesParam.height = _resourceHeight;
        switch (themeType) {
        case "DotDensity":
            picName = "ThemeDotDensity.gif";
            var dotDemsityTheme = theme;
            if (dotDemsityTheme != null) {
                var imgID = _layers[index].name + "_ThemeDotDensity_IMG";
                resourcesParam.style = dotDemsityTheme.dotStyle;
                resourcesParam.resourceType = 0;
                resourcesParam.imageFormat = _imageFormat;
                themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + dotDemsityTheme.dotValue + "</div>"
            }
            break;
        case "GraduatedSymbol":
            picName = "ThemeGraduatedSymbol.gif";
            var graduatedSymbolTheme = theme;
            if (graduatedSymbolTheme != null) {
                var imgID;
                imgID = _layers[index].name + "_ThemeGraph_IMG_0";
                resourcesParam.style = graduatedSymbolTheme.styleForPositive;
                resourcesParam.resourceType = 0;
                resourcesParam.imageFormat = _imageFormat;
                themeInnerHTML += '<Div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + SuperMap.IS.LayerControlResource.positive + "</div>";
                if (graduatedSymbolTheme.showNegative == true) {
                    imgID = _layers[index].name + "_ThemeGraph_IMG_1";
                    resourcesParam.style = graduatedSymbolTheme.styleForNegative;
                    resourcesParam.resourceType = 0;
                    resourcesParam.imageFormat = _imageFormat;
                    themeInnerHTML += '<Div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + SuperMap.IS.LayerControlResource.negative + "</div>"
                }
            }
            break;
        case "Graph":
            picName = "ThemeGraph.gif";
            var graphTheme = theme;
            if (graphTheme != null) {
                for (var j = 0; j < graphTheme.graphStyles.length; j++) {
                    var imgID = _layers[index].name + "_ThemeGraph_IMG" + j;
                    resourcesParam.style = graphTheme.graphStyles[j];
                    resourcesParam.resourceType = 2;
                    resourcesParam.imageFormat = _imageFormat;
                    themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + graphTheme.expressions[j] + "</div>"
                }
            }
            break;
        case "Label":
            picName = "ThemeLabel.gif";
            break;
        case "Range":
            picName = "ThemeRange.gif";
            var rangeTheme = theme;
            if (rangeTheme != null) {
                for (var j = 0; j < rangeTheme.displays.length; j++) {
                    var imgID = _layers[index].name + "_ThemeRange_IMG" + j;
                    resourcesParam.style = rangeTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    var value = "";
                    if (j == 0) {
                        value = "x<" + rangeTheme.breakValues[j]
                    } else {
                        if (j == rangeTheme.displays.length - 1) {
                            value = rangeTheme.breakValues[j - 1] + "<=x"
                        } else {
                            value = rangeTheme.breakValues[j - 1] + "<=x<" + rangeTheme.breakValues[j]
                        }
                    }
                    themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + value + "</div>"
                }
            }
            break;
        case "Unique":
            picName = "ThemeUnique.gif";
            var uniqueTheme = theme;
            if (uniqueTheme != null) {
                var imgID = _layers[index].name + "_ThemeUnique_IMG";
                for (var j = 0; j < uniqueTheme.values.length; j++) {
                    resourcesParam.style = uniqueTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    themeInnerHTML += '<div><img id="' + imgID + '"src="' + map.GenerateResourceRequest(resourcesParam) + '">' + uniqueTheme.itemCaptions[j] + "</div>"
                }
            }
            break;
        case "Custom":
            themeName = SuperMap.IS.ThemeResource.themeCustom;
            picName = "ThemeCustom.gif";
            break;
        case "GridRange":
            picName = "ThemeRange.gif";
            var gridRangeTheme = theme;
            if (gridRangeTheme != null) {
                for (var j = 0; j < gridRangeTheme.displays.length; j++) {
                    var imgID = _layers[index].name + "_ThemeRange_IMG" + j;
                    resourcesParam.style = new SuperMap.IS.Style();
                    resourcesParam.style.brushColor = gridRangeTheme.displays[j];
                    resourcesParam.resourceType = resourceType;
                    resourcesParam.imageFormat = _imageFormat;
                    if (gridRangeTheme.breakValues[0] == null) {
                        str = '<tr><td valign="top" width="20px">';
                        continue
                    }
                    var value = "";
                    if (j == 0) {
                        value = "x< " + gridRangeTheme.breakValues[j]
                    } else {
                        if (j == gridRangeTheme.displays.length - 1) {
                            value = gridRangeTheme.breakValues[j - 1] + "<=x"
                        } else {
                            value = gridRangeTheme.breakValues[j - 1] + "<=x<" + gridRangeTheme.breakValues[j]
                        }
                    }
                    themeInnerHTML += '<div><img id="' + imgID + '" src="' + map.GenerateResourceRequest(resourcesParam) + '" >' + value + "</div>"
                }
            }
            break
        }
        str += '</td><td><table><tr><td valign="top" width="20px"> <img src="images/' + picName + '"> </td><td>';
        if (_themeNameEnabled == true) {
            str += themeName
        }
        str += "</td><tr><td></td><td>";
        str += '<div id="' + _id + "_" + _layers[index].name + "_" + themeType + '" style="display:block">';
        str += themeInnerHTML;
        str += "</div></td></tr></table></td>";
        str += '<td valign="top" width="20px">';
        if (theme.enabled) {
            str += "<input id='" + _id + "_" + index + "_" + themeType + "' type=checkbox checked ";
            str += "></td>"
        } else {
            str += "<input id='" + _id + "_" + index + "_" + themeType + "' type=checkbox  ";
            str += "></td>"
        }
        return str
    }
    function WriteEndScriptPart() {
        var str = "</tr>";
        return str
    }
    function onGetResourceComplete(url, userContext) {
        var img = document.getElementById(userContext);
        img.src = url
    }
    function onGetResourceError() {}
    this.SwitchThemesDisplay = _SwitchThemesDisplay;
    function _SwitchThemesDisplay(e) {
        var strId = e.srcElement.id;
        var strNew = strId.replace("Switch_", "");
        var img = document.getElementById(strId);
        var o = document.getElementById(strNew);
        if (o == null || img == null) {
            return
        }
        if (o.style.display == "block") {
            o.style.display = "none";
            img.src = "images/collapse.gif"
        } else {
            o.style.display = "block";
            img.src = "images/expand.gif"
        }
    }
    this.SwitchThemesVisible = _SwitchThemesVisible;
    function _SwitchThemesVisible(e) {
        var strids = new Array();
        strIds = e.srcElement.id.split("_");
        var index = strIds[1];
        var themeType = strIds[2];
        var value = document.getElementById(e.srcElement.id).checked;
        if (!index || !themeType) {
            return
        }
        if (index == -1) {
            return
        }
        switch (themeType) {
        case "DotDensity":
            if (_layers[index].themeDotDensity) {
                _layers[index].themeDotDensity.enabled = value
            }
            break;
        case "GraduatedSymbol":
            if (_layers[index].themeGraduatedSymbol) {
                _layers[index].themeGraduatedSymboly.enabled = value
            }
            break;
        case "Graph":
            if (_layers[index].themeGraph) {
                _layers[index].themeGraph.enabled = value
            }
            break;
        case "Label":
            if (_layers[index].themeLabel) {
                _layers[index].themeLabel.enabled = value
            }
            break;
        case "Range":
            if (_layers[index].themeRange) {
                _layers[index].themeRange.enabled = value
            }
            break;
        case "Unique":
            if (_layers[index].themeUnique) {
                _layers[index].themeUnique.enabled = value
            }
            break;
        case "Custom":
            if (_layers[index].themeCustom) {
                _layers[index].themeCustom.enabled = value
            }
            break;
        case "GridRange":
            if (_layers[index].themeGridRange) {
                _layers[index].themeGridRange.enabled = value
            }
            break
        }
        strids = null;
        index = null;
        themeType = null;
        value = null;
        map.Update();
        _TriggerEvent("themeswitched", null, "ThemeSwitched")
    }
    this.OnContextMenu = function(e, index) {
        e = _GetEvent(e);
        _CancelBubble(e);
        e.index = index;
        if (_curLayerContextMenu && _curLayerContextMenu.OpenContextMenu) {
            _curLayerContextMenu.OpenContextMenu(e, _self)
        }
        return false
    };
    function _SetLayerContextMenu(layerContextMenu) {
        if (layerContextMenu) {
            layerContextMenu.Init(_map)
        }
        _curLayerContextMenu = layerContextMenu;
        _curLayerContextMenu.Init(_map);
        _SetLayerContextMenuHidden()
    }
    function _SetLayerContextMenuHidden() {
        var hidden = document.getElementById(container.id + "_hiddenLayerContextMenu");
        if (hidden) {
            hidden.value = _curLayerContextMenu.json
        }
    }
    function _SetLayerItemsHidden() {
        var layerItemsJSON = _ToJSON(_layerItems);
        var hidden = document.getElementById(container.id + "_hiddenLayerItems");
        if (hidden) {
            hidden.value = layerItemsJSON
        }
    }
    function _SaveLayerStatus() {
        var changed = false;
        for (var i = 0; i < map.layers.length; i++) {
            var layer = map.layers[i];
            if (layer) {
                var theme = layer.themeDotDensity;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_DotDensity");
                    if (theme.enable != checkboxTheme.checked) {
                        theme.enable = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeGraduatedSymbol;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_GraduatedSymbol");
                    if (theme.enable != checkboxTheme.checked) {
                        theme.enable = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeGraph;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Graph");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeLabel;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Label");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeRange;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Range");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeUnique;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Unique");
                    if (theme.enabled != checkboxTheme.checked) {
                        theme.enabled = checkboxTheme.checked;
                        changed = true
                    }
                }
                theme = layer.themeGridRange;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_GridRange");
                    if (checkboxTheme != null) {
                        if (theme.enabled != checkboxTheme.checked) {
                            theme.enabled = checkboxTheme.checked;
                            changed = true
                        }
                    }
                }
                theme = layer.themeCustom;
                if (theme) {
                    var checkboxTheme = document.getElementById(_id + "_" + i + "_Custom");
                    if (checkboxTheme != null) {
                        if (theme.enabled != checkboxTheme.checked) {
                            theme.enabled = checkboxTheme.checked;
                            changed = true
                        }
                    }
                }
            }
            var checkboxV = document.getElementById(container.id + "_" + layer.name + ":V");
            if (checkboxV) {
                var visible = checkboxV.checked;
                if (_layerItems[i].visibleChecked != visible) {
                    map.layers[i].visible = visible;
                    _layerItems[i].visibleChecked = visible;
                    changed = true
                }
            }
            var checkboxQ = document.getElementById(container.id + "_" + layer.name + ":Q");
            if (checkboxQ) {
                var queryable = checkboxQ.checked;
                if (_layerItems[i].queryableChecked != queryable) {
                    map.layers[i].queryable = queryable;
                    _layerItems[i].queryableChecked = queryable;
                    changed = true
                }
            }
        }
        if (changed) {
            map.Update();
            _TriggerEvent("layeritemschanged", null, "LayerItemsChanged")
        }
    }
    this.CheckedAllQueryable = _CheckedAllQueryable;
    function _CheckedAllQueryable() {
        var queryableCheckboxAll = document.getElementById(container.id + "_CheckQueryableAll");
        for (var i = 0; i < map.layers.length; i++) {
            var layer = map.layers[i];
            var queryableCheckbox = document.getElementById(container.id + "_" + layer.name + ":Q");
            if (queryableCheckbox) {
                queryableCheckbox.checked = queryableCheckboxAll.checked
            }
        }
        _checkedAllQueryable = queryableCheckboxAll.checked
    }
    this.CheckedAllVisible = _CheckedAllVisible;
    function _CheckedAllVisible() {
        var visibleCheckboxAll = document.getElementById(container.id + "_CheckVisibleAll");
        for (var i = 0; i < map.layers.length; i++) {
            var layer = map.layers[i];
            var visibleCheckbox = document.getElementById(container.id + "_" + layer.name + ":V");
            if (visibleCheckbox) {
                visibleCheckbox.checked = visibleCheckboxAll.checked
            }
        }
        _checkedAllVisible = visibleCheckboxAll.checked
    }
    function _SortLayers(sortType, isDescend) {
        if (!isDescend) {
            isDescend = false
        }
        for (var i = 0; i < _indexs.length - 1; i++) {
            for (var j = i + 1; j < _indexs.length; j++) {
                var interchange = false;
                if (_Compare(_layerItems[_indexs[i]], _layerItems[_indexs[j]], sortType) == isDescend) {
                    interchange = true
                }
                if (!interchange) {
                    var tempIndex = _indexs[i];
                    _indexs[i] = _indexs[j];
                    _indexs[j] = tempIndex
                }
            }
        }
    }
    function _SortLayerItem(sortType, isDescend) {
        _SortLayers(sortType, isDescend);
        _RenderLayers(this)
    }
    function _Compare(layerItemP, layerItemN, sortType) {
        var valueP;
        var valueN;
        switch (sortType) {
        case 0:
            valueP = layerItemP.value;
            valueN = layerItemN.value;
            break;
        case 1:
            valueP = layerItemP.caption;
            valueN = layerItemN.caption;
            break;
        case 2:
            valueP = layerItemP.order;
            valueN = layerItemN.order;
            break
        }
        if (valueP >= valueN) {
            return true
        } else {
            return false
        }
    }
    function _UpdateCheckBox(layer) {
        var checkboxV = document.getElementById(container.id + "_" + layer.name + ":V");
        var checkboxQ = document.getElementById(container.id + "_" + layer.name + ":Q");
        if (layer.visible) {
            checkboxV.checked = "checked"
        } else {
            checkboxV.checked = ""
        }
        if (layer.queryable) {
            checkboxQ.checked = "checked"
        } else {
            checkboxQ.checked = ""
        }
    }
    function _Update() {
        _ResetLayerItems(map.layers);
        _inited = false;
        _Init()
    }
    function _GetResult(arg) {
        if (_filterResult.join(",") == arg) {
            return
        }
        _filterResult = arg.split(",");
        if (_lastMapName != map.mapName) {
            _layers = map.layers;
            for (var i = 0; i < _layers.length; i++) {
                _indexs[i] = i
            }
            _lastMapName = map.mapName
        }
        if (_isShowInCurrentPage) {
            _RenderLayers()
        } else {
            container.style.visibility = "hidden"
        }
    }
    this.GetLayerItemByIndex = function(index) {
        if (index == null || typeof index == "undefined") {
            return null
        }
        if (!_layerItems) {
            return null
        }
        if (_layerItems[index]) {
            return _layerItems[index]
        }
        return null
    };
    this.GetLayerItemByValue = function(value) {
        if (!_layerItems || value == null || typeof value == "undefined") {
            return null
        }
        for (var i = 0; i < _layerItems.length; i++) {
            if (_layerItems[i].value == value) {
                return _layerItems[i]
            }
        }
    }
}