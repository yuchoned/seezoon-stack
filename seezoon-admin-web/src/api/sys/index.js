import request from "@/utils/request";
import qs from 'qs'
import {Dict} from "@/utils/dict";

/**
 * 部门树
 * @param parentId
 * @param includeChild
 * @returns {Promise<AxiosResponse<any>>}
 */
export function deptTree(parentId, includeChild) {
    return request.post('/sys/dept/tree', qs.stringify({parentId: parentId, includeChild: includeChild}))
}

/**
 * 获取全部字典类型
 * @returns {Promise<[]>}
 */
export async function getTypes() {
    let {data} = await request.get('/sys/dict/queryTypes');
    let dictTypes = [];
    for (let type of data.values()) {
        dictTypes.push(new Dict(type, type))
    }
    return dictTypes;
}

/**
 * 获取全部角色
 * @returns {Promise<[]>}
 */
export async function getRoles() {
    let {data} = await request.get('/sys/role/query');
    let roles = [];
    for (let role of data.values()) {
        roles.push(new Dict(role.name, role.id, role.status === 0))
    }
    return roles;
}