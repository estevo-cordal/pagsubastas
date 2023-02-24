const getModuleState = state => state.bids;

export const getOrderSearch = state =>
    getModuleState(state).orderSearch;

export const getBid = state =>
    getModuleState(state).bid;
