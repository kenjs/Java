package tf56.contract.services;

import java.util.Map;

public interface LbsDeviceService {
	public String selectLbsTrackByDeviceIdAndParyId(Map map);

	public String selectLbsTrackListByIds(Map map);

	public String seletLbsFenceListByIds(Map map);
	/**
     * @author wei.huang
     * @date 2014-1-21
     * @function 调度单跟踪
     */
    public String track(Map map);
    /**
     * @author wei.huang
     * @date 2014-1-21
     * @function 调度单回放
     */
    public String playback(Map map);
}
