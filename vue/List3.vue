<template>
	<div>
		<el-form :inline="true" :model="searchF" class="demo-form-inline">
			<el-form-item label="名称">
				<el-input v-model="searchF.name" placeholder="名称"></el-input>
			</el-form-item>

			<el-form-item>
				<el-button type="primary" @click="initData()">查询</el-button>
			</el-form-item>
		</el-form>
		<el-button type="primary" @click="changeInsert()">添加</el-button>

		<el-table :data="tableData" style="width: 100%">
			<el-table-column prop="uid" label="编号">
			</el-table-column>
			<el-table-column prop="name" label="名称">
			</el-table-column>
			<el-table-column prop="username" label="登录名称">
			</el-table-column>

			<el-table-column prop="sex" label="性别">
			</el-table-column>
			<el-table-column prop="state" label="状态">
			</el-table-column>
			<el-table-column prop="dept.name" label="部门">
			</el-table-column>

			<el-table-column label="操作">
				<template v-slot="scope">
					<el-button type="primary" @click="del(scope.row.uid)">删除</el-button>
					<el-button type="primary" @click="update(scope.row)">修改</el-button>
				</template>
			</el-table-column>

		</el-table>
		<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[3]"
		 :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
		</el-pagination>


		<el-dialog title="添加" :visible.sync="changeInsertLog">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="名称">
					<el-input v-model="form.username"></el-input>
				</el-form-item>
				<el-form-item label="性别">
					<el-select v-model="form.sex" placeholder="性别">
						<el-option label="男" :value="1"></el-option>
						<el-option label="女" :value="2"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="出生日期">
					<el-date-picker placeholder="选择时间" value-format="yyyy-MM-dd" v-model="form.birthday" style="width: 100%;"></el-date-picker>

				</el-form-item>
				<el-form-item label="爱好">
					<el-checkbox-group v-model="likee">
						<el-checkbox v-for="like in likes" :label="like.id">{{like.name}}</el-checkbox>
					</el-checkbox-group>
				</el-form-item>
				<el-form-item label="身份证">
					<el-input v-model="form.card.name"></el-input>
				</el-form-item>
				<el-form-item label="部门">
					<el-select v-model="form.dept.id" clearable placeholder="请选择">
						<el-option v-for="item in depts" :label="item.name" :value="item.id">
							{{item.name}}
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="房子">
					<el-table :data="form.rooms" style="width: 100%">
						<el-table-column prop="name" label="name" width="90">
						</el-table-column>

						<el-table-column fixed="right" label="操作" width="100">
							<template slot-scope="scope">
								<el-button @click="editRoom(scope.row)" type="text" size="small">编辑</el-button>
								<el-button type="text" size="small" @click="delRoom(scope.row)">删除</el-button>
							</template>
						</el-table-column>
					</el-table>
					<el-form :inline="true" :model="roomData" class="demo-form-inline">
						<el-form-item label="名称">
							<el-input v-model="roomData.name" placeholder="名称"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="addRoom">添加</el-button>
						</el-form-item>
					</el-form>
				</el-form-item>

				<el-form-item>
					<el-button type="primary" @click="onSubmit()">立即创建</el-button>
					<el-button>取消</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>




	</div>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					uid:"",
					birthday: "",
					code: "",
					email: "",
					name: "",
					username: "",
					sex: "",
					state: 0,
					card: {
						name: "",
					},
					dept: {
						id: ""
					},
					rooms: [],
					likes: []
				},
				roomData: {
					name: ""
				},
				changeInsertLog: false,
				searchF: {
					name: "",
				},
				likee: [],
				page: 1,
				pageSize: 3,
				total: 0,
				pages: 0,
				tableData: [],
				depts: [],
				likes: [],
			}
		},
		created: function() {
			this.initData();
			this.initDepts();
			this.initLikes();
		},
		methods: {
			initLikes() {
				this.axios.get("http://127.0.0.1:8200/user/likes").then((msg) => {
					this.likes = msg.data;
				})
			},
			initDepts() {
				this.axios.get("http://127.0.0.1:8200/user/depts").then((msg) => {
					this.depts = msg.data;
				})
			},
			initData() {
				this.axios.get("http://127.0.0.1:8200/user/list", {
					params: {
						page: this.page,
						pageSize: this.pageSize,
						username: this.searchF.name
					}
				}).then((msg) => {
					this.tableData = msg.data.content;
					this.pages = msg.data.totalPages;
					this.page = msg.data.number + 1;
					this.pageSize = msg.data.size;
					this.total = msg.data.totalElements;
				})
			},
			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
			},
			handleCurrentChange(val) {
				this.page = val;
				this.initData();
			},
			del(id) {
				this.axios.get("http://127.0.0.1:8200/user/del", {
					params: {
						id: id
					}

				}).then((msg) => {
					if (msg.data) {
						alert("成功")
						this.page = 1;
						this.initData();
					}
				})
			},
			changeInsert() {
				this.changeInsertLog = true
			},
			onSubmit() {
				console.log(this.likee);
				//Object.assign(this.form.likes,this.like);
				for (let i = 0; i < this.likee.length; i++) {
					var obj = new Object(); 
					obj.id=this.likee[i];
					this.form.likes.push(obj)
				}
				
				console.log(this.form.likes)


				this.axios.post("http://127.0.0.1:8200/user/saveOrUpdate", this.form).then((msg) => {
					if (msg.data) {
						alert("成功")
						this.changeInsertLog = false;
						this.form= {
							uid:"",
							birthday: "",
							code: "",
							email: "",
							name: "",
							username: "",
							sex: "",
							state: 0,
							card: {
								name: "",
							},
							dept: {
								id: ""
							},
							rooms: [],
							likes: []
						}
						this.likee=[];
						this.initData();
					}
				})
			},
			//添加房屋
			addRoom() {
				var newRoom = new Object();
				Object.assign(newRoom, this.roomData)

				this.form.rooms.push(newRoom);

			},
			//删除房屋
			delRoom(rData) {
				let rindex = this.form.rooms.indexOf(rData);
				console.log("要删除的索引 " + rindex)
				this.form.rooms.splice(rindex, 1)
			},
			editRoom(roomData) {
				this.roomData = roomData
			},
			update(row){
				this.form=row;
				//this.likee=row.likes;
				
				for (let i = 0; i < row.likes.length; i++) {
					this.likee.push(row.likes[i].id)
				}
				this.form.likes=[];
				console.log(this.likee)
				this.changeInsertLog=true;
			}
		}
	}
</script>

<style>
</style>
