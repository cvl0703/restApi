package zv.cvl.restapi.viewmodel

data class DataType (var createdAt: String, var name: String, var avatar: String, var id: String) {
    override fun toString(): String {
        return "El número de usuario: ${this.id} \n" +
                "fue creado ${this.createdAt}, tiene como nombre ${this.name} y su avatar es ${this.avatar}\n"
    }
}