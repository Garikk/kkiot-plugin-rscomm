package kkdev.kksystem.plugin.rscomm.manager;


import kkdev.kksystem.base.classes.base.PinDataTaggedString;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerBase;
import kkdev.kksystem.base.constants.PluginConsts;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_BASIC_TAGGEDOBJ_DATA;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.plugin.rscomm.configuration.PluginSettings;
import kkdev.kksystem.plugin.rscomm.KKPlugin;
import kkdev.kksystem.plugin.rscomm.adapters.IRSAdapter;
import kkdev.kksystem.plugin.rscomm.adapters.rs232.RS232;
import kkdev.kksystem.plugin.rscomm.configuration.RSConfig;
import kkdev.kksystem.plugin.rscomm.configuration.ServicesConfig;

public class RSManager extends PluginManagerBase {

    private IRSAdapter Adapter;
    KKPlugin LocalConnector;

    public void Init(KKPlugin Conn) {
        setPluginConnector(Conn);
        LocalConnector = Conn;
        //Init Adapters and start scan and connect
        this.currentFeature.put(SystemConsts.KK_BASE_UICONTEXT_DEFAULT, PluginSettings.MainConfiguration.FeatureID);
        //
        ConfigAndInitHW();
        //
    }

    public void Start() {
        Adapter.StartAdapter(LocalConnector.GetUtils(), this);
    }

    private void ConfigAndInitHW() {
        //Init HW adapter
        if (PluginSettings.MainConfiguration.BTAdapter == RSConfig.AdapterTypes.jsscRS232) {
            //TODO TEMPORARY!!!
            Adapter = new RS232(this, PluginSettings.MainConfiguration.RSServicesMapping[0].DevAddr);
            //Set up services
            for (ServicesConfig SVC : PluginSettings.MainConfiguration.RSServicesMapping) {
                Adapter.RegisterService(SVC);
            }
            //Adapter.StartAdapter(this);
        }
    }

    public void RS_ReceiveData(String Tag, String Data) {
        PinDataTaggedString ObjDat;
        ObjDat = new PinDataTaggedString();
        ObjDat.tag = Tag;
        ObjDat.value = Data;

        this.BASE_SendPluginMessage(SystemConsts.KK_BASE_FEATURES_SYSTEM_MULTIFEATURE_UID,SystemConsts.KK_BASE_UICONTEXT_DEFAULT, PluginConsts.KK_PLUGIN_BASE_BASIC_TAGGEDOBJ_DATA, ObjDat);
    }

    public void ReceivePIN(PluginMessage Msg) {
        if (Msg.pinName.equals(KK_PLUGIN_BASE_BASIC_TAGGEDOBJ_DATA)) {
            PinDataTaggedString PIN = (PinDataTaggedString) Msg.getPinData();
            //
            if (!PIN.tag.equals("SMARTHEAD")) {
                return;
            }
            //
            Adapter.SendStringData(PIN.tag, (String) PIN.value);
        }
    }

}
